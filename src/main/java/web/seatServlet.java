package web;

import ejb.RequestBean;
import entity.Seat;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


@WebServlet("/seatServlet")
public class seatServlet extends HttpServlet {
    @EJB
    private RequestBean ejb;

    private SeatManager seatManager;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);

    }

    //更安全
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String s = request.getParameter("seatId");
        String method=request.getParameter("method");

        if(method.equals("status")) {
            try {
                PrintWriter w = response.getWriter();
                Seat seat = ejb.getSeatbySeatId(s);
                if (seat.getStatus().equals("空闲")) {
                    w.write("0");
                } else if (seat.getStatus().equals("已使用")) {
                    w.write("1");
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else if(method.equals("changeStatus")){
            Seat seat = ejb.getSeatbySeatId(s);
            seat.setStatus("已使用");
            ejb.updateSeat(seat);
        }

    }
}
