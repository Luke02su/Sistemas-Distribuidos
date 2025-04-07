package com.sd.prjweb_sd.servlets;

import com.poo2.prjweb_sd.servlets.BuscarArquivo;
import com.poo2.prjweb_sd.servlets.GravarArquivo;
import com.poo2.prjweb_sd.servlets.Pet;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;

/**
 *
 * @author augus
 */
@WebServlet(name = "GerenciarDados", urlPatterns = {"/GerenciarDados"})
public class GerenciarDados extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String acao = request.getParameter("acao");
        String parteAcao[] = acao.split("-");
        int index = 0;
        if(parteAcao.length > 1) {
            acao = "Mostra_Obj";
            index = Integer.parseInt(parteAcao[1].trim())-1;
        }
        System.out.println(acao);
        System.out.println(index);
        try {
           switch(acao) {
                case "Salvar":
                   salvarPets(request, response);
                   break;
                case "Listar":
                    getPets(request, response);
                    break;
                case "Mostra_Obj":
                    getPets(request, response, index);
                    break;
           }
        } catch(Exception ex) {
            throw new ServletException(ex);
        }
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

    private void salvarPets(HttpServletRequest request,
HttpServletResponse response) throws ServletException, IOException {
        Pet pet = new Pet();
        pet.setNome(request.getParameter("nome"));
        pet.setRaca(request.getParameter("raca"));
        pet.setPorte(request.getParameter("porte"));
        pet.setDataNasc(request.getParameter("data_nasc"));
        pet.setCor(request.getParameter("cor"));
        new GravarArquivo().escreverArq(pet);
        String path = "/TestePaginaServlet.jsp";
        RequestDispatcher dispatcher = request.getRequestDispatcher(path);
        dispatcher.forward(request, response);
    }
    
    private void getPets(HttpServletRequest request,
HttpServletResponse response) throws IOException, ServletException {
        List<Pet> lstPets = new BuscarArquivo().lerArq();
        request.setAttribute("lstPets", lstPets);
        String path = "/TestePaginaServlet.jsp";
        RequestDispatcher dispatcher = request.getRequestDispatcher(path);
        dispatcher.forward(request, response);
    }
    
    private void getPets(HttpServletRequest request,
HttpServletResponse response, int index) throws IOException, ServletException {
        List<Pet> lstPets = new BuscarArquivo().lerArq();
        Pet pet = lstPets.get(index);
        request.setAttribute("pet", pet);
        String path = "/TestePaginaServlet.jsp";
        RequestDispatcher dispatcher = request.getRequestDispatcher(path);
        dispatcher.forward(request, response);
    }
}
