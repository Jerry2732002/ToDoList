<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>View ToDo Task</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <style>
        body {
            background-color: #f8f9fa;
        }
        .task-container {
            margin-top: 50px;
        }
        .task-card {
            margin-bottom: 20px;
        }
        .task-title {
            font-size: 1.5rem;
            font-weight: bold;
        }
        .task-status {
            font-size: 1.2rem;
            color: #28a745;
        }
        .error-message {
            color: #dc3545;
            font-weight: bold;
        }
        .btn-container {
            margin-top: 20px;
        }
        .add-task-container {
            margin-top: 30px;
            text-align: right;
        }
    </style>
</head>
<body>
    <div class="container task-container">
        <h1 class="text-center">ToDo Tasks</h1>

        <!-- Add Task Button -->
        <div class="add-task-container">
            <form action="/addTaskForm" method="get" style="display:inline;">
                <button type="submit" class="btn btn-danger">Add Task</button>
            </form>
        </div>

        <c:choose>
            <c:when test="${not empty tasks}">
                <c:forEach var="task" items="${tasks}">
                    <div class="card task-card">
                        <div class="card-body">
                            <h5 class="card-title task-title">${task.task}</h5>
                            <p class="card-text">Created Date: ${task.created_date}</p>
                            <p class="card-text">Deadline: ${task.deadline}</p>
                            <p class="card-text">Status: ${task.status}</p>
                            <div class="btn-container">
                                <form action="/updateStatus" method="post" style="display:inline;">
                                    <input type="hidden" name="id" value="${task.id}">
                                    <button type="submit" class="btn btn-success">Done</button>
                                </form>
                                <form action="/editTask" method="get" style="display:inline;">
                                    <input type="hidden" name="id" value="${task.id}">
                                    <button type="submit" class="btn btn-primary">Edit</button>
                                </form>
                                <form action="/deleteTask/${task.id}" method="post" th:method="delete" style="display:inline;">
                                    <button type="submit" class="btn btn-danger">Delete</button>
                                </form>

                            </div>
                        </div>
                    </div>
                </c:forEach>
            </c:when>
            <c:otherwise>
                <p class="error-message text-center">No tasks found.</p>
            </c:otherwise>
        </c:choose>
    </div>

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
