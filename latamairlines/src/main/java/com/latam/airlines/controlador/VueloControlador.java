package com.latam.airlines.controlador;

import com.latam.airlines.modelo.Vuelo;
import com.latam.airlines.modelo.Destino;
import com.latam.airlines.modelo.Aeropuerto;
import com.latam.airlines.modelo.dao.VueloDAO;
import com.latam.airlines.modelo.dao.DestinoDAO;
import com.latam.airlines.modelo.dao.AeropuertoDAO;
import com.latam.airlines.modelo.dao.AvionDAO;
import com.latam.airlines.modelo.dao.ClaseVueloDAO;
import com.latam.airlines.utils.ResourcesUtils;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "VueloControlador", urlPatterns = {"/vuelo"})
public class VueloControlador extends HttpServlet {

    private final String pagListar = "/pagGestVuelo.jsp";
    private final String pagNuevo = "/pagNuevoVuelo.jsp";
    private VueloDAO vueloDAO;
    private DestinoDAO destinoDAO;
    private AeropuertoDAO aeropuertoDAO;
    private AvionDAO avionDAO;
    private ClaseVueloDAO claseVueloDAO;

    @Override
    public void init(ServletConfig config) throws ServletException {
        vueloDAO = new VueloDAO();
        destinoDAO = new DestinoDAO();
        aeropuertoDAO = new AeropuertoDAO();
        avionDAO = new AvionDAO();
        claseVueloDAO = new ClaseVueloDAO();
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
        request.setAttribute("vuelos", vueloDAO.listarTodos());
        request.getRequestDispatcher(pagListar).forward(request, response);
    }

    private void nuevo(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("vuelo", new Vuelo());
        request.setAttribute("destinos", destinoDAO.listarTodosOrdAscPais());
        request.setAttribute("aeropuertos", aeropuertoDAO.listarTodos());
        request.setAttribute("claseVuelos", claseVueloDAO.listarTodos());
        request.setAttribute("aviones", avionDAO.listarTodos());
        request.setAttribute("tipoVuelos", ResourcesUtils.listTipoVuelo());
        request.getRequestDispatcher(pagNuevo).forward(request, response);
    }

    private void guardar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Vuelo vuelo = new Vuelo();
        try {
            vuelo.setIdVuelo(Integer.parseInt(request.getParameter("idVuelo")));
            vuelo.setDestinoOrigen(new Destino(Integer.parseInt(request.getParameter("origenId"))));
            vuelo.setDestinoRegreso(new Destino(Integer.parseInt(request.getParameter("destinoId"))));
            vuelo.setFechaSalida(request.getParameter("fecha"));
            vuelo.setHoraSalida(request.getParameter("hora"));

            boolean result;
            if (vuelo.getIdVuelo() == 0) {
                result = vueloDAO.guardar(vuelo);
            } else {
                result = vueloDAO.actualizar(vuelo);
            }

            if (result) {
                request.getSession().setAttribute("success", vuelo.getIdVuelo() == 0 ? "Vuelo registrado" : "Vuelo actualizado");
                response.sendRedirect("vuelo?accion=listar");
                return;
            }
            request.getSession().setAttribute("error", "No se pudo guardar los datos.");
        } catch (Exception ex) {
            request.getSession().setAttribute("error", ex.getMessage());
        }

        request.setAttribute("vuelo", vuelo);
        request.setAttribute("destinos", destinoDAO.listarTodosOrdAscPais());
        request.setAttribute("aeropuertos", aeropuertoDAO.listarTodos());
        request.setAttribute("aviones", avionDAO.listarTodos());
        request.setAttribute("claseVuelos", claseVueloDAO.listarTodos());
        request.setAttribute("tipoVuelos", ResourcesUtils.listTipoVuelo());
        request.getRequestDispatcher(pagNuevo).forward(request, response);
    }

    private void editar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Vuelo vuelo = vueloDAO.buscarPorId(id);

        if (vuelo != null) {
            request.setAttribute("vuelo", vuelo);
            request.setAttribute("destinos", destinoDAO.listarTodosOrdAscPais());
            request.setAttribute("aeropuertos", aeropuertoDAO.listarTodos());
            request.setAttribute("aviones", avionDAO.listarTodos());
            request.setAttribute("claseVuelos", claseVueloDAO.listarTodos());
            request.setAttribute("tipoVuelos", ResourcesUtils.listTipoVuelo());
            request.getRequestDispatcher(pagNuevo).forward(request, response);
        } else {
            request.getSession().setAttribute("error", "No se encontró el vuelo con ID " + id);
            response.sendRedirect("vuelo?accion=listar");
        }
    }

    private void eliminar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            boolean result = vueloDAO.eliminar(id);

            if (result) {
                request.getSession().setAttribute("success", "Vuelo con id " + id + " eliminado!");
            } else {
                request.getSession().setAttribute("error", "No se pudo eliminar el vuelo");
            }
        } catch (Exception ex) {
            request.getSession().setAttribute("error", ex.getMessage());
        }

        response.sendRedirect("vuelo?accion=listar");
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
