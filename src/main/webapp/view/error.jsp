<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <!DOCTYPE html>
    <html>

    <head>
        <title>Error Page</title>
        <link rel="stylesheet" href="styles.css">
        <style>
            /* CSS code for error page */
            body {
                background-color: #f8f9fa;
                font-family: Arial, sans-serif;
                display: flex;
                justify-content: center;
                align-items: center;
                height: 100vh;
            }

            .error-container {
                text-align: center;
            }

            .error-code {
                font-size: 72px;
                font-weight: bold;
                color: #dc3545;
            }

            .error-message {
                font-size: 24px;
                color: #333;
            }

            .home-link {
                display: inline-block;
                margin-top: 20px;
                padding: 10px 20px;
                background-color: #007bff;
                color: #fff;
                text-decoration: none;
                border-radius: 4px;
                transition: background-color 0.3s ease;
            }

            .home-link:hover {
                background-color: #0056b3;
            }
        </style> <!-- Link to external CSS file -->
    </head>

    <body>
        <div class="error-container">
            <div class="error-code">${status}</div>
            <div class="error-message">Something Went Wrong</div>

        </div>
    </body>

    </html>