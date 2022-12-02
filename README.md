# Middle-OOP
Виконали: Ахинько Катерина, Казимир Арсеній, Васильків Дмитро, Кузьма Володимир.

При виконанні виккористано декілька паттернів ООП:
- **Singleton**: companyService, companyController та companyRepository усі мають єдиний instance, адже представляють об'єкти, що потрібні в єдиному екземплярі для роботи сервісу.
- **Strategy**: усі класи-парсери зведено до єдиного інтерфейсу, щоб усі вони містили метод парсу, що повертатиме рівно один екземпляр класу Company.
- **Builder**: для класу Company використано анотацію Builder, адже у нього багато полів, що не завжди повністю заповнені і окремі види конструктора тут були б незручними.


Use case diagram:
https://drive.google.com/file/d/1xxr-3i4mXiHH0BmF5T5L4C6LaSCFu6Ur/view?usp=sharing<br>
Class diagram: 
https://drive.google.com/file/d/1VLJ6gTnFrBDK-_GJrxOqvY6M1MgkXKaR/view?usp=share_link
