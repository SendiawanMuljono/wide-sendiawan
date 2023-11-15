# Wide-Sendiawan
Simple CRUD Wide project using Spring Boot Java

Setup Database
1. Install XAMPP
2. Buka XAMPP kemudian start Apache dan MySQL
3. Pilih Admin pada baris MySQL dalam XAMPP
4. Terbuka phpMyAdmin kemudian pilih New dan masukan "wide" sebagai database name
5. Klik Create
6. Pilih "wide" pada bagian kiri lalu pilih "Import" pada bagian atas
7. Pilih Choose File, lalu pilih wide.sql dan klik Go pada bawah kanan

Setup Backend
1. Buat folder kosong contohnya "sts-workspace" sebagai workspace dari SpringToolSuite4
2. Pindahkan folder "wide-sendiawan" ke dalam sts-workspace tersebut
3. Buka SpringToolSuite4 dan pilih directory "sts-workspace" tersebut lalu klik Launch
4. Klik File > Import > Existing Projects into Workspace > Browse... > pilih directory dari folder "wide-sendiawan" > Finish
5. Folder "wide-sendiawan" akan ada pada sebelah kiri di dalam Package Explorer
6. Klik kanan folder "wide-sendiawan" > Run As > Maven Install
7. Setelah dependencies terinstall, klik kanan folder "wide-sendiawan" > Run As > Spring Boot App
8. Backend berhasil dijalankan pada http://localhost:8080
 
