<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Cinema Hall Movie Booking System - README</title>
</head>
<body>

<h1>Cinema Hall Movie Booking System</h1>

<p>This project is a full-fledged web application designed to mimic a cinema hall's movie booking system. It was developed as part of a team project for a software engineering course, with a focus on learning and implementing real-world software development practices.</p>

<h2>Features</h2>
<ul>
  <li><strong>Browse Movies</strong>: Users can browse through a list of movies currently playing in the cinema.</li>
  <li><strong>Seat Selection & Booking</strong>: Users can select seats and book them securely.</li>
  <li><strong>Cart and Checkout</strong>: Booked tickets are added to the cart for checkout.</li>
  <li><strong>Session Management</strong>: User sessions are tracked, ensuring a smooth and personalized booking experience.</li>
</ul>

<h2>Technology Stack</h2>
<ul>
  <li><strong>Backend</strong>: Java Spring Boot (MVC Architecture)</li>
  <li><strong>Frontend</strong>: HTML, CSS, JavaScript (minimal contribution)</li>
  <li><strong>Database</strong>: MySQL</li>
</ul>

<h2>My Role</h2>
<p>As the <strong>team leader</strong> and <strong>backend developer</strong>, I was responsible for:</p>
<ul>
  <li>Designing and implementing the <strong>services layer</strong> using Java Spring Boot.</li>
  <li>Ensuring <strong>data consistency</strong>, specifically preventing:
    <ul>
      <li>Double-booking of seats for the same movie session.</li>
      <li>Scheduling conflicts where two movies would be booked in the same hall at the same time.</li>
    </ul>
  </li>
  <li>Implementing <strong>session tracking</strong> to manage user activities such as seat reservations and checkout flow.</li>
  <li>Collaborating with front-end and database teams to ensure seamless integration.</li>
</ul>

<p>This project successfully demonstrated my ability to lead a team, design scalable backend solutions, and manage critical data integrity in a real-world software system.</p>

<h2>How to Run</h2>
<ol>
  <li>Clone the repository.</li>
  <li>Ensure you have Java, Spring Boot, and MySQL installed.</li>
  <li>Run the application using Spring Boot <code>mvn spring-boot:run</code>.</li>
  <li>Access the application via <code>http://localhost:8080</code>.</li>
</ol>

</body>
</html>

