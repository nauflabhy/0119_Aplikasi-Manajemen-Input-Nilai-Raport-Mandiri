# 📱 Aplikasi Manajemen Input Nilai Raport Mandiri

Aplikasi berbasis Android untuk memudahkan guru atau wali kelas dalam mengelola data siswa, menginput nilai mata pelajaran, menghitung nilai akhir secara otomatis, serta mencetak/mengekspor raport secara mandiri.

---

## 📋 Daftar Isi
- [Fitur Utama](#-fitur-utama)
- [Jadwal Progres Pengerjaan](#-jadwal-progres-pengerjaan)
- [Teknologi yang Digunakan](#-teknologi-yang-digunakan)
- [Cara Menjalankan Proyek](#-cara-menjalankan-proyek)

---

## ✨ Fitur Utama
- **Manajemen Master Data:** Tambah, lihat, ubah, dan hapus (CRUD) data kelas, mata pelajaran, dan siswa.
- **Input & Kalkulasi Nilai:** Input nilai komponen (Tugas, UTS, UAS) dengan perhitungan nilai akhir dan predikat otomatis.
- **Pratinjau & Ekspor Raport:** Menampilkan ringkasan nilai raport per siswa dan ekspor ke format PDF/Excel.

---

## 📅 Jadwal Progres Pengerjaan

Proyek ini dikerjakan secara bertahap selama **8 Minggu** dengan target *milestone* mingguan sebagai berikut:

### Fase 1: Analisis & Perancangan Sistem
- [ ] **Minggu 9: Pembuatan Dokumen SRS**
  - Menentukan ruang lingkup aplikasi.
  - Mendata kebutuhan fungsional dan non-fungsional.
  - Menyelesaikan draf dokumen *Software Requirements Specification* (SRS). [DALAM PROGRES]
  - Pembuatan Use Case Diagram, Activity Diagram, ERD, RAT, Flow diagram
  - Perancangan struktur tabel database MySQL
  - Pembuatan wireframe alur aplikasi multi-halaman

### Fase 2: 
- [ ] **Minggu 10: Setup Project & Autentikasi** 🔄 *(sedang dikerjakan)*
  - [x] Setup project Android (Kotlin, Jetpack Compose, Material 3) dan dependensi Gradle
  - [x] Struktur folder MVVM (data, navigation, ui, util)
  - [x] Navigation Compose (rute Login, Register, Dashboard)
  - [x] UI Login dan Register beserta validasi form
  - [x] `LoginViewModel` dan `RegisterViewModel` (state dengan StateFlow)
  - [ ] Membuat `schema.sql` dan `seed.sql` dari ERD ⏳
  - [ ] Setup backend (Node.js + Express + MySQL) ⏳
  - [ ] Endpoint `register`, `login`, `refresh`, dan `logout` dengan JWT ⏳
  - [ ] Integrasi Retrofit dan penyimpanan token di DataStore (menggantikan `FakeAuthRepository`) ⏳

> **Catatan:** Login dan Register saat ini masih memakai data uji (*fake repository*),
> belum tersambung ke server. Integrasi ke backend dikerjakan setelah endpoint autentikasi selesai.
