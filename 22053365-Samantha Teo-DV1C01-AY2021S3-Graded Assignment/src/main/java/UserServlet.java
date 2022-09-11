import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;

/**
 * Servlet implementation class UserServlet
 */
@WebServlet("/UserServlet")
public class UserServlet extends HttpServlet {
//private static final long serialVersionUID = 1L;


	private String jdbcURL = "jdbc:mysql://localhost:3306/userdetails";
	private String jdbcUsername = "root";
	private String jdbcPassword = "password";

	private static final String INSERT_USERS_SQL = "INSERT INTO UserDetails"
			+ " (name, password, email, address) VALUES " + " (?, ?, ?);";
	private static final String SELECT_USER_BY_ID = "select name,password,email,address from UserDetails where name =?";
	private static final String SELECT_ALL_USERS = "select * from UserDetails ";
	private static final String DELETE_USERS_SQL = "delete from UserDetails where name = ?;";
	private static final String UPDATE_USERS_SQL = "update UserDetails set name = ?,password= ?, email =?,address =? where name = ?;";


	protected Connection getConnection() {

		Connection connection = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return connection;
	}

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
// TODO Auto-generated method stub
		String action = request.getServletPath();
		try {
			switch (action) {
			
			  case "/insert": break; case "/delete": break; case "/edit": break; case
			 "/update": break; default: listUsers(request, response); break;
			 

			case "/UserServlet/delete":
				deleteUser(request, response);
				break;

			case "/UserServlet/edit":
				showEditForm(request, response);
				break;
			case "/UserServlet/update":
				updateUser(request, response);
				break;
			case "/UserServlet/dashboard":
				listUsers(request, response);
				break;
			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
		}

		response.getWriter().append("Served at: ").append(request.getContextPath());
	}


	private void showEditForm(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {

		String name = request.getParameter("name");
		Users existingUser = new Users("", "", "", "");

		try (Connection connection = getConnection();

				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_ID);) {
			preparedStatement.setString(1, name);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				name = rs.getString("name");
				String password = rs.getString("password");
				String email = rs.getString("email");
				String address = rs.getString("address");
				existingUser = new Users(name, password, email, address);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

		request.setAttribute("user", existingUser);

		request.getRequestDispatcher("/userEdit.jsp").forward(request, response);
	}


	private void updateUser(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {

		String oriName = request.getParameter("oriName");
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		String email = request.getParameter("email");
		String address = request.getParameter("address");

		
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(UPDATE_USERS_SQL);) {
			statement.setString(1, name);
			statement.setString(2, password);
			statement.setString(3, email);
			statement.setString(4, address);
			statement.setString(5, oriName);
			int i = statement.executeUpdate();
		}
		
		response.sendRedirect("http://localhost:8090/HelloWorldJaveEE/UserServlet/dashboard");
	}


	private void deleteUser(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {

		String name = request.getParameter("name");
		
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(DELETE_USERS_SQL);) {
			statement.setString(1, name);
			int i = statement.executeUpdate();
		}
	

		response.sendRedirect("http://localhost:8090/HelloWorldJaveEE/UserServlet/dashboard");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
// TODO Auto-generated method stub
		doGet(request, response);
	}


	private void listUsers(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		List<Users> users = new ArrayList<>();
		try (Connection connection = getConnection();
			
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_USERS);) {
			
			ResultSet rs = preparedStatement.executeQuery();
		
			while (rs.next()) {
				String name = rs.getString("name");
				String password = rs.getString("password");
				String email = rs.getString("email");
				String address = rs.getString("address");
				users.add(new Users(name, password, email, address));
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

		request.setAttribute("listUsers", users);
		request.getRequestDispatcher("/userManagement.jsp").forward(

				request, response);
	}
}
