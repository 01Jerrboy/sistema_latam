package com.latam.airlines.controlador;

import com.latam.airlines.modelo.Avion;
import com.latam.airlines.modelo.dao.AvionDAO;
import jakarta.servlet.ServletConfig;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "AvionControlador", urlPatterns = {"/avion"})
public class AvionControlador extends HttpServlet {

    private final String pagListar = "/pagGestAvion.jsp";
    private final String pagNuevo = "/pagNuevoAvion.jsp";
    private AvionDAO avionDAO;

    @Override
    public void init(ServletConfig config) throws ServletException {
        avionDAO = new AvionDAO();
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
            boolean result = avionDAO.eliminar(id);

            if (result) {
                request.getSession().setAttribute("success", "Avión con id " + id + " eliminado!");
            } else {
                request.getSession().setAttribute("error", "No se pudo eliminar el avión");
            }
        } catch (Exception ex) {
            request.getSession().setAttribute("error", ex.getMessage());
        }

        response.sendRedirect("avion?accion=listar");
    }

    private void editar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Avion avion = avionDAO.buscarPorId(id);

        if (avion != null) {
            request.setAttribute("avion", avion);
            request.getRequestDispatcher(pagNuevo).forward(request, response);
        } else {
            request.getSession().setAttribute("error", "No se encontró el avión con ID " + id);
            response.sendRedirect("avion?accion=listar");
        }
    }

    private void guardar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Avion obj = new Avion();
        try {
            obj.setIdAvion(Integer.parseInt(request.getParameter("id")));
            obj.setModelo(request.getParameter("modelo"));
            obj.setCapacidad(Integer.parseInt(request.getParameter("capacidad")));
            obj.setFabricante(request.getParameter("fabricante"));
            obj.setAnnioFabricacion(Integer.parseInt(request.getParameter("annioFabricacion")));

            boolean result;
            if (obj.getIdAvion() == 0) {
                result = avionDAO.guardar(obj);
            } else {
                result = avionDAO.actualizar(obj);
            }

            if (result) {
                request.getSession().setAttribute("success", obj.getIdAvion() == 0 ? "Avión registrado" : "Avión actualizado");
                response.sendRedirect("avion?accion=listar");
                return;
            }
            request.getSession().setAttribute("error", "No se pudo guardar los datos.");
        } catch (Exception ex) {
            request.getSession().setAttribute("error", ex.getMessage());
        }

        request.setAttribute("avion", obj);
        request.getRequestDispatcher(pagNuevo).forward(request, response);
    }

    private void nuevo(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("avion", new Avion());
        request.getRequestDispatcher(pagNuevo).forward(request, response);
    }

    protected void listar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("aviones", avionDAO.listarTodos());
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
