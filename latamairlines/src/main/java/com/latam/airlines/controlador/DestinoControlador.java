package com.latam.airlines.controlador;

import com.latam.airlines.modelo.Destino;
import com.latam.airlines.modelo.Pais;
import com.latam.airlines.modelo.Aeropuerto;
import com.latam.airlines.modelo.dao.DestinoDAO;
import com.latam.airlines.modelo.dao.PaisDAO;
import com.latam.airlines.modelo.dao.AeropuertoDAO;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "DestinoControlador", urlPatterns = {"/destino"})
public class DestinoControlador extends HttpServlet {

    private final String pagListar = "/pagGestDestino.jsp";
    private final String pagNuevo = "/pagNuevoDestino.jsp";
    private DestinoDAO destinoDAO;
    private PaisDAO paisDAO;
    private AeropuertoDAO aeropuertoDAO;

    @Override
    public void init(ServletConfig config) throws ServletException {
        destinoDAO = new DestinoDAO();
        paisDAO = new PaisDAO();
        aeropuertoDAO = new AeropuertoDAO();
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        String accion = request.getParameter("accion");

        switch (accion) {
            case "listar":
                listar(request, response);
                break;
            case "nuevo":
                nuevo(request, response);
                break;
            case "guardar":
                guardar(request, response);
                break;
            case "editar":
                editar(request, response);
                break;
            case "eliminar":
                eliminar(request, response);
                break;
            default:
                throw new AssertionError();
        }
    }

    private void listar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("destinos", destinoDAO.listarTodos());
        request.getRequestDispatcher(pagListar).forward(request, response);
    }

    private void nuevo(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("destino", new Destino());
        request.setAttribute("paises", paisDAO.ListarTodos());
        request.setAttribute("aeropuertos", aeropuertoDAO.listarTodos());
        request.getRequestDispatcher(pagNuevo).forward(request, response);
    }

    private void guardar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Destino destino = new Destino();
        try {
            destino.setIdDestino(Integer.parseInt(request.getParameter("id")));
            destino.setCiudad(request.getParameter("ciudad"));
            destino.setPais(new Pais(Integer.parseInt(request.getParameter("paisId"))));
            destino.setAeropuerto(new Aeropuerto(Integer.parseInt(request.getParameter("aeropuertoId"))));

            boolean result;
            if (destino.getIdDestino() == 0) {
                result = destinoDAO.guardar(destino);
            } else {
                result = destinoDAO.actualizar(destino);
            }

            if (result) {
                request.getSession().setAttribute("success", destino.getIdDestino() == 0 ? "Destino registrado" : "Destino actualizado");
                response.sendRedirect("destino?accion=listar");
                return;
            }
            request.getSession().setAttribute("error", "No se pudo guardar los datos.");
        } catch (Exception ex) {
            request.getSession().setAttribute("error", ex.getMessage());
        }

        request.setAttribute("destino", destino);
        request.setAttribute("paises", paisDAO.ListarTodos());
        request.setAttribute("aeropuertos", aeropuertoDAO.listarTodos());
        request.getRequestDispatcher(pagNuevo).forward(request, response);
    }

    private void editar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Destino destino = destinoDAO.buscarPorId(id);

        if (destino != null) {
            request.setAttribute("destino", destino);
            request.setAttribute("paises", paisDAO.ListarTodos());
            request.setAttribute("aeropuertos", aeropuertoDAO.listarTodos());
            request.getRequestDispatcher(pagNuevo).forward(request, response);
        } else {
            request.getSession().setAttribute("error", "No se encontró el destino con ID " + id);
            response.sendRedirect("destino?accion=listar");
        }
    }

    private void eliminar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            boolean result = destinoDAO.eliminar(id);

            if (result) {
                request.getSession().setAttribute("success", "Destino con id " + id + " eliminado!");
            } else {
                request.getSession().setAttribute("error", "No se pudo eliminar el destino");
            }
        } catch (Exception ex) {
            request.getSession().setAttribute("error", ex.getMessage());
        }

        response.sendRedirect("destino?accion=listar");
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
