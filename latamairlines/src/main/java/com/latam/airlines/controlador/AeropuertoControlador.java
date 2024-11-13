package com.latam.airlines.controlador;

import com.latam.airlines.modelo.Aeropuerto;
import com.latam.airlines.modelo.Pais;
import com.latam.airlines.modelo.dao.AeropuertoDAO;
import com.latam.airlines.modelo.dao.PaisDAO;
import jakarta.servlet.ServletConfig;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "AeropuertoControlador", urlPatterns = {"/aeropuerto"})
public class AeropuertoControlador extends HttpServlet {

    private final String pagListar = "/pagGestAeropuerto.jsp";
    private final String pagNuevo = "/pagNuevoAeropuerto.jsp";
    private AeropuertoDAO aeropuertoDAO;
    private PaisDAO paisDAO;

    @Override
    public void init(ServletConfig config) throws ServletException {
        aeropuertoDAO = new AeropuertoDAO();
        paisDAO = new PaisDAO();
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

    private void eliminar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            boolean result = aeropuertoDAO.eliminar(id);

            if (result) {
                request.getSession().setAttribute("success", "Aeropuerto con id " + id + " eliminado!");
            } else {
                request.getSession().setAttribute("error", "No se pudo eliminar el aeropuerto");
            }
        } catch (Exception ex) {
            request.getSession().setAttribute("error", ex.getMessage());
        }

        response.sendRedirect("aeropuerto?accion=listar");
    }

    private void editar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Aeropuerto aeropuerto = aeropuertoDAO.buscarPorId(id);

        if (aeropuerto != null) {
            request.setAttribute("aeropuerto", aeropuerto);
            request.setAttribute("paises", paisDAO.ListarTodos());
            request.getRequestDispatcher(pagNuevo).forward(request, response);
        } else {
            request.getSession().setAttribute("error", "No se encontró el aeropuerto con ID " + id);
            response.sendRedirect("aeropuerto?accion=listar");
        }
    }

    private void guardar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Aeropuerto obj = new Aeropuerto();
        try {
            obj.setIdAeropuerto(Integer.parseInt(request.getParameter("id")));
            obj.setNombre(request.getParameter("nombre"));
            obj.setCodigo(request.getParameter("codigo"));

            boolean result;
            if (obj.getIdAeropuerto() == 0) {
                result = aeropuertoDAO.guardar(obj);
            } else {
                result = aeropuertoDAO.actualizar(obj);
            }

            if (result) {
                request.getSession().setAttribute("success", obj.getIdAeropuerto() == 0 ? "Aeropuerto registrado" : "Aeropuerto actualizado");
                response.sendRedirect("aeropuerto?accion=listar");
                return;
            }
            request.getSession().setAttribute("error", "No se pudo guardar los datos.");
        } catch (Exception ex) {
            request.getSession().setAttribute("error", ex.getMessage());
        }

        request.setAttribute("aeropuerto", obj);
        request.setAttribute("paises", paisDAO.ListarTodos());
        request.getRequestDispatcher(pagNuevo).forward(request, response);
    }

    private void nuevo(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("aeropuerto", new Aeropuerto());
        request.setAttribute("paises", paisDAO.ListarTodos());
        request.getRequestDispatcher(pagNuevo).forward(request, response);
    }

    protected void listar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("aeropuertos", aeropuertoDAO.listarTodos());
        request.getRequestDispatcher(pagListar).forward(request, response);
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
