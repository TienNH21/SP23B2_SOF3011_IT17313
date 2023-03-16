package repositories;

import view_model.QLKhachHang;

import java.util.ArrayList;

public class KhachHangRepository {
    private ArrayList<QLKhachHang> list;

    public KhachHangRepository()
    {
        this.list = new ArrayList<>();
    }

    public void insert(QLKhachHang kh)
    {
        // INSERT INTO KhachHang(ma, ten_dem, ten, ...) VALUES (?, ?, ?, ...)
        this.list.add(kh);
    }

    public void update(QLKhachHang kh)
    {
        // UPDATE KhachHang SET ho = ?, ten_dem = ?, ten = ?, ... WHERE ma = ?
        for (int i = 0; i < this.list.size(); i++) {
            QLKhachHang vm = this.list.get(i);
            if (vm.getMa().equals(kh.getMa())) {
                this.list.set(i, kh);
            }
        }
    }

    public void delete(QLKhachHang kh)
    {
        // DELETE FROM KhachHang WHERE ma = ?
        for (int i = 0; i < this.list.size(); i++) {
            QLKhachHang vm = this.list.get(i);
            if (vm.getMa().equals(kh.getMa())) {
                this.list.remove(i);
            }
        }
    }

    public ArrayList<QLKhachHang> findAll()
    {
        return this.list;
    }

    public QLKhachHang findByMa(String ma)
    {
        // SELECT * FROM KhachHang WHERE ma = ?
        for (int i = 0; i < this.list.size(); i++) {
            QLKhachHang vm = this.list.get(i);
            if (vm.getMa().equals(ma)) {
                return this.list.get(i);
            }
        }

        return null;
    }
}
