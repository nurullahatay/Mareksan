# Matbaa Sipariş Takip Sistemi

>Matbaa tarafından siparişin girilmesini ve müşterinin siparişinin durumunu takip etmesi sağlayan uygulamadır.

Web Sayfası:[link](https://mareksan.herokuapp.com)


## Kullanılan Teknolojiler ve Kütüphaneler
##### Front-end  
- Html
- CSS
- JQuery
- SweetAlert
- DataTables
- Bootstrap 

##### Back-end  
- Spring Boot
- Spring Data Jpa
- Spring Cache 
- Spring Schedule
- Log4j

##### Database
- MySql

## Genel Bilgiler
- Giriş yapan kullanıcının rolüne göre(CUSTOMER yada ADMIN) sayfalara erişim.
- Yeni sipariş oluşturma, düzenleme, silme ve pdf dosyasını oluşturma.
- Yeni Admin ile Customer(Müşteri) oluşturma, düzenleme ve silme.
- Sipariş cinsi oluşturma, düzenleme ve silme.
- Müşteri tarafında müşterinin kendi siparişlerinin gösterilmesi.
- Siparişler ve sipariş cinslerinde cache işlemi.
- Yapılan işlemlerin çoğu ajax ile yapıldı.
- Kullanıcıların girdiği bilgilerde validate işlemi yapıldı(now only back-end).

## Görseller
1. Admin Giriş Sayfası 
![1](https://user-images.githubusercontent.com/17932770/50551445-7f79f100-0c91-11e9-82ab-054e80ac29ed.PNG)
2. Admin Müşteri Ekleme Formu
![2](https://user-images.githubusercontent.com/17932770/50551446-7f79f100-0c91-11e9-84df-08b61c8a0310.PNG)
 3. Admin Müşteri Listesi
![3](https://user-images.githubusercontent.com/17932770/50551447-80128780-0c91-11e9-877d-cc4368cf8e42.PNG)
4. Admin Sipariş Ekleme Formu 
![4](https://user-images.githubusercontent.com/17932770/50551448-80128780-0c91-11e9-8174-72fcc945cc57.PNG)
5. Admin Sipariş Cinsi 
![5](https://user-images.githubusercontent.com/17932770/50551449-80ab1e00-0c91-11e9-8233-7f9feed96fd0.PNG)
6. Müşteri Giriş Sayfası 
![6](https://user-images.githubusercontent.com/17932770/50551485-0cbd4580-0c92-11e9-8977-4aa759c1ded2.PNG)

### Çalıştırmak İçin
http://localhost:8080 yada https://mareksan.herokuapp.com adreslerinden bu kullanıcılar ile giriş yapabilirsiniz:
```
Admin:
email : sirke2t@gmail.com 
şifre : 123456

Customer:
email : sirket@gmail.com 
şifre : 123456
```
