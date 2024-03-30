<%@ page import="java.util.List" %>
<%@ page import="org.unibl.etf.model.bean.QuestionBean" %>
<%@ page import="org.unibl.etf.model.dto.QuestionDTO" %>
<%@ page import="org.unibl.etf.model.dto.AdvisorDTO" %>

<%
	AdvisorDTO advisor = (AdvisorDTO)session.getAttribute("advisor");
	if(advisor == null || !advisor.isLoggedIn()){
		response.sendRedirect("login.jsp");
	}
    // Update seen status
    if ("seen".equals(request.getParameter("action"))) {
        int id = Integer.parseInt(request.getParameter("id"));
        QuestionBean questionBean = new QuestionBean();
        boolean seenStatus = questionBean.setSeen(id);
        out.println(seenStatus); // Return seen status to AJAX call
        return;
    }
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Fitness Program Inbox</title>
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css" rel="stylesheet">
</head>
<body>
    <%@ include file="navbar.jsp" %>
    <div class="container mt-5">
        <h1 class="mb-4">Fitness Program Inbox</h1>
        
        <!-- Search bar -->
        <div class="input-group mb-3">
            <input type="text" class="form-control" id="searchInput" placeholder="Search messages...">
            <div class="input-group-append">
                <button class="btn btn-outline-secondary" type="button" id="searchButton"><i class="fas fa-search"></i></button>
            </div>
        </div>
        
        <!-- Displaying messages -->
        <%
            QuestionBean questionBean = new QuestionBean();
            List<QuestionDTO> questions = questionBean.getAllQuestions();
            for (QuestionDTO question : questions) {
        %>
        <div class="card">
            <div class="card-header d-flex justify-content-between align-items-center">
                <h5 class="mb-0">
                    <button class="btn btn-link" data-toggle="collapse" data-target="#message<%=question.getId()%>" aria-expanded="true" aria-controls="message<%=question.getId()%>"
                            onclick="markAsSeen(<%= question.getId() %>)">
                        <%= question.getUserName() + " " + question.getUserSurname() + " - " + question.getProgramTitle() + " - " + question.getDate() %>
                    </button>
                </h5>
                <span id="seenIcon<%= question.getId() %>">
                    <% if (question.getSeen()) { %>
                        <i class="fas fa-check-circle text-success"></i>
                    <% } else { %>
                        <i class="far fa-circle text-secondary"></i>
                    <% } %>
                </span>
            </div>

            <div id="message<%=question.getId()%>" class="collapse" aria-labelledby="heading<%=question.getId()%>">
                <div class="card-body">
                    <p><%= question.getContent() %></p>
                    <button type="button" class="btn btn-primary" onclick="reply('<%= question.getUserEmail() %>')">
					    <i class="fas fa-reply"></i> Reply
					</button>
                </div>
            </div>
        </div>
        <% } %>
    </div>

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    <script>
    
    function reply(userEmail) {
        window.location.href = 'reply.jsp?userEmail=' + encodeURIComponent(userEmail);
    }
    
    // Update the seen icon and send AJAX request to mark question as seen
    function markAsSeen(questionId) {
        // Retrieve the current icon status
        var iconSpan = document.getElementById("seenIcon" + questionId);
        var isSeen = iconSpan.innerHTML.includes('text-success');

        // Update the seen status only if it's currently not seen
        if (!isSeen) {
            $.ajax({
                type: "GET",
                url: "inbox.jsp",
                data: {
                    action: "seen",
                    id: questionId
                },
                success: function(response) {
                    // Update the seen icon based on the response
                    updateSeenIcon(questionId, true);
                },
                error: function(xhr, status, error) {
                    console.error("Error occurred while marking as seen: " + error);
                }
            });
        }
    }

    // Update the seen icon
    function updateSeenIcon(questionId, seen) {
        var iconSpan = document.getElementById("seenIcon" + questionId);
        if (seen) {
            iconSpan.innerHTML = '<i class="fas fa-check-circle text-success"></i>';
        } else {
            iconSpan.innerHTML = '<i class="far fa-circle text-secondary"></i>';
        }
    }

    $(document).ready(function() {
        $('#searchButton').click(function() {
            var searchText = $('#searchInput').val().toLowerCase();
            $('.card').each(function() {
                var text = $(this).text().toLowerCase();
                if (text.indexOf(searchText) === -1) {
                    $(this).hide();
                } else {
                    $(this).show();
                }
            });
        });
    });
</script>
</body>
</html>
