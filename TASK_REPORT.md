# Báo Cáo Thực Thi Nhiệm Vụ: Tích Hợp Chức Năng Thêm Người (Add Person) Vào Menu Worker

## 1. Mục tiêu nhiệm vụ
- **Kiểm tra hiện trạng:** Xác định vị trí code của chức năng thêm người (`addPerson`) và xóa người (`deleteByID`) trong hệ thống.
- **Tích hợp tính năng:** Đưa chức năng thêm người vào menu giao diện của cấp Worker (`MenuUserLv02`) để người dùng có thể nhập thông tin và thêm người trực tiếp từ giao diện dòng lệnh.
- **Đảm bảo tính tương thích:** Tránh xung đột số thứ tự menu với lớp kế thừa cấp trên (`MenuUserLv01`), đảm bảo biên dịch và toàn bộ các test cases hiện có đều vượt qua.

---

## 2. Kết quả kiểm tra vị trí chức năng ban đầu

| Chức năng | Vị trí định nghĩa | Trạng thái ban đầu |
| :--- | :--- | :--- |
| **Thêm người** (`addPerson`) | [`src/smartsite/PersonManager.java`](file:///d:/D/DocumentAcademic/FPT_COURSE/2_PRO192/CodeLab/Project8/SMARTSITE_AI_INTEGRATED_CONSTRUCTION_SITE_MANAGEMENT_SYSTEM/src/smartsite/PersonManager.java#L26-L42) | Chỉ được gọi tạo sẵn dữ liệu tĩnh trong `Main.java` (dòng 57-59), chưa có giao diện nhập liệu. |
| **Xóa người** (`deleteByID`) | [`src/smartsite/PersonManager.java`](file:///d:/D/DocumentAcademic/FPT_COURSE/2_PRO192/CodeLab/Project8/SMARTSITE_AI_INTEGRATED_CONSTRUCTION_SITE_MANAGEMENT_SYSTEM/src/smartsite/PersonManager.java#L75-L88) | Chưa được gọi ở bất kỳ menu nào; logic mới tìm thấy index và in thông báo, chưa dồn mảng hoặc giảm `size`. |

---

## 3. Các thay đổi và triển khai chi tiết

### 3.1. Cập nhật Menu Worker ([`MenuUserLv02.java`](file:///d:/D/DocumentAcademic/FPT_COURSE/2_PRO192/CodeLab/Project8/SMARTSITE_AI_INTEGRATED_CONSTRUCTION_SITE_MANAGEMENT_SYSTEM/src/ui/MenuUserLv02.java))
1. **Bổ sung mục lựa chọn:**
   - Thêm lựa chọn `6. Add person` vào phương thức `displayOption()`.
2. **Bổ sung điều hướng lựa chọn:**
   - Trong `chooseOption(int choice)`, bắt `case (6): handleAddPerson();`.
3. **Hiện thực hàm nhập liệu `handleAddPerson()`:**
   - **Nhập ID:** Kiểm tra chuỗi rỗng và kiểm tra trùng ID đã có bằng cách gọi `pm.findPersonByID(id)`.
   - **Nhập Họ tên:** Kiểm tra chuỗi rỗng.
   - **Nhập Mã định danh/quẹt thẻ (Code):** Phục vụ đăng nhập hệ thống.
   - **Phân loại đối tượng nhập:**
     - `1. Worker`: Cho phép nhập thêm `Company Name` và `Contract Type` (Full-time / Part-time), khởi tạo đối tượng `Worker`.
     - `2. Visitor`: Cho phép nhập thêm `Visit Purpose` và `Host Name`, khởi tạo đối tượng `Visitor`.
     - `3. Other Person`: Nhập `Role` tùy chỉnh (e.g. `CONTRACTOR`, `SAFETY_OFFICER`), khởi tạo đối tượng `Person`.
   - **Ghi nhận & Thông báo:** Gọi `pm.addPerson(newPerson)` và in kết quả trực quan (thành công / thất bại).

### 3.2. Điều chỉnh xung đột thứ tự lựa chọn ([`MenuUserLv01.java`](file:///d:/D/DocumentAcademic/FPT_COURSE/2_PRO192/CodeLab/Project8/SMARTSITE_AI_INTEGRATED_CONSTRUCTION_SITE_MANAGEMENT_SYSTEM/src/ui/MenuUserLv01.java))
- Vì `MenuUserLv01` (Site Manager / Safety Officer) kế thừa từ `MenuUserLv02`, chức năng riêng của Lv01 trước đây là lựa chọn 6 (`6. Show all history attendance`).
- Đã được cập nhật thành **lựa chọn 7** (`7. Show all history attendance`) để kế thừa trọn vẹn cả lựa chọn 5 (`Take tools`) và lựa chọn 6 (`Add person`) của `MenuUserLv02`.

### 3.3. Tự động mở rộng kích thước mảng ([`PersonManager.java`](file:///d:/D/DocumentAcademic/FPT_COURSE/2_PRO192/CodeLab/Project8/SMARTSITE_AI_INTEGRATED_CONSTRUCTION_SITE_MANAGEMENT_SYSTEM/src/smartsite/PersonManager.java#L31-L35))
- Ban đầu mảng `people` có kích thước cố định là 10. Khi thêm người thứ 11, hàm `addPerson` sẽ trả về `false`.
- Cập nhật cơ chế: Khi `size >= people.length`, hệ thống tự động khởi tạo mảng mới gấp đôi kích thước (`people.length * 2`) và sao chép dữ liệu cũ qua `System.arraycopy`.

---

## 4. Kết quả kiểm thử (Verification)

1. **Biên dịch mã nguồn:**
   - Chạy lệnh `javac -encoding UTF-8 -d bin ...` thành công không có lỗi cú pháp.
2. **Kiểm thử bộ Test Cases hệ thống:**
   - Chạy `java -cp bin smartsite.TestCaseRunner`: Toàn bộ 10/10 test cases (từ TC01 đến TC10) đều đạt kết quả **PASSED**.
