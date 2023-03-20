package controllers;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.apache.commons.beanutils.BeanUtils;
import repositories.KhachHangRepository;
import view_model.QLKhachHang;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

@WebServlet({
    "/khach-hang/index",    // GET
    "/khach-hang/create",   // GET
    "/khach-hang/edit",     // GET
    "/khach-hang/delete",   // GET
    "/khach-hang/store",    // POST
    "/khach-hang/update",   // POST
})
public class KhachHangServlet extends HttpServlet {
    private KhachHangRepository khRepo;

    public KhachHangServlet()
    {
        this.khRepo = new KhachHangRepository();
        this.khRepo.insert(new QLKhachHang("PH1", "Ng", "Van", "A", "12-12-2000", "0123", "HN", "1", "vi", "HN"));
        this.khRepo.insert(new QLKhachHang("PH2", "Tr", "Thi", "B", "12-12-2000", "0123", "HN", "1", "vi", "HN"));
    }

    @Override
    protected void doGet(
        HttpServletRequest request,
        HttpServletResponse response
    ) throws ServletException, IOException {
        String uri = request.getRequestURI();
        if (uri.contains("create")) {
            this.create(request, response);
        } else if (uri.contains("edit")) {
            this.edit(request, response);
        } else if (uri.contains("delete")) {
            this.delete(request, response);
        } else {
            this.index(request, response);
        }
    }

    protected void edit(
        HttpServletRequest request,
        HttpServletResponse response
    ) throws ServletException, IOException {
        String ma = request.getParameter("ma");
        QLKhachHang kh = this.khRepo.findByMa(ma);
        request.setAttribute("kh", kh);
        request.getRequestDispatcher("/views/khach_hang/edit.jsp")
            .forward(request, response);
    }


    protected void delete(
        HttpServletRequest request,
        HttpServletResponse response
    ) throws ServletException, IOException {
        String ma = request.getParameter("ma");
        QLKhachHang kh = this.khRepo.findByMa(ma);
        this.khRepo.delete(kh);
        response.sendRedirect("/SP23B2_SOF3011_IT17313_war_exploded/khach-hang/index");
    }

    @Override
    protected void doPost(
        HttpServletRequest request,
        HttpServletResponse response
    ) throws ServletException, IOException {
        String uri = request.getRequestURI();
        if (uri.contains("store")) {
            this.store(request, response);
        } else if (uri.contains("update")) {
            this.update(request, response);
        } else {
            // 404
            // 405
            response.sendRedirect("/SP23B2_SOF3011_IT17313_war_exploded/khach-hang/index");
        }
    }

    protected void update(
        HttpServletRequest request,
        HttpServletResponse response
    ) throws ServletException, IOException {
        String ma = request.getParameter("ma");
        String ho = request.getParameter("ho");
        String tenDem = request.getParameter("ten_dem");
        String ten = request.getParameter("ten");
        String ngay_sinh = request.getParameter("ngay_sinh");
        String sdt = request.getParameter("sdt");
        String mat_khau = request.getParameter("mat_khau");
        String dia_chi = request.getParameter("dia_chi");
        String quoc_gia = request.getParameter("quoc_gia");
        String thanh_pho = request.getParameter("thanh_pho");
        System.out.println(ma);
        System.out.println(ten);

        QLKhachHang vm = new QLKhachHang(ma, ten, tenDem, ho, ngay_sinh, sdt, dia_chi, mat_khau, quoc_gia, thanh_pho);
        this.khRepo.update(vm);

        response.sendRedirect("/SP23B2_SOF3011_IT17313_war_exploded/khach-hang/index");
    }

    protected void store(
        HttpServletRequest request,
        HttpServletResponse response
    ) throws ServletException, IOException {
        try {
            QLKhachHang vm = new QLKhachHang();
            BeanUtils.populate(vm, request.getParameterMap());
            this.khRepo.insert(vm);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        response.sendRedirect("/SP23B2_SOF3011_IT17313_war_exploded/khach-hang/index");
    }

    protected void create(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {
        request.getRequestDispatcher("/views/khach_hang/create.jsp")
            .forward(request, response);
    }

    protected void index(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {
        request.setAttribute("danhSachKH", this.khRepo.findAll());
        request.setAttribute("view", "/views/khach_hang/index.jsp");
        request.getRequestDispatcher("/views/layout.jsp").forward(request, response);
    }
}
