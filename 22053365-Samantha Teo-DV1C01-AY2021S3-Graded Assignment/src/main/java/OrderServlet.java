
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;

/**
 * Servlet implementation class OrderServlet
 */
@WebServlet("/OrderServlet")
public class OrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String jdbcURL = "jdbc:mysql://localhost:3306/menu";
	private String jdbcUsername = "root";
	private String jdbcPassword = "password";

	private static final String INSERT_ORDERS_SQL = "INSERT INTO menu"
			+ " (order, side, drink, payment, orderid) VALUES " + "(?,?,?,?,?);";
	private static final String SELECT_ORDER_BY_ID = "select order, side, drink, payment, orderid from menu where orderid =?";
	private static final String SELECT_ALL_ORDER = "select * from menu ";
	private static final String DELETE_ORDER_SQL = "delete from menu where orderid = ?;";
	private static final String UPDATE_ORDER_SQL = "update menu set orderid = ?,order= ?, side =?,drink =? where payment = ? where orderid =?;";

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
	public OrderServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action = request.getServletPath();
		try {
			switch (action) {
			case "/insert": break; case "/delete": break; case "/edit": break; 
			case"/update": break; default: listOrder(request, response); break;

			case "/UserServlet/delete":
				deleteOrder(request, response);
				break;

			case "/UserServlet/edit":
				showEditForm(request, response);
				break;
			case "/UserServlet/update":
				updateOrder(request, response);
				break;
			case "/UserServlet/dashboard":
				listOrder(request, response);
				break;
			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
		
		}
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	private void showEditForm(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		String orderid = request.getParameter("orderid");
		Order existingOrder = new Order("", "", "", "", "");
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ORDER_BY_ID);) {
			preparedStatement.setString(1, orderid);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				orderid = rs.getString("orderid");
				String order = rs.getString("order");
				String side = rs.getString("side");
				String drink = rs.getString("drink");
				String payment = rs.getString("payment");
				orderid = rs.getString("orderid");
				existingOrder = new Order(order, side, drink, payment, orderid);

			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		request.setAttribute(orderid, existingOrder);
		request.getRequestDispatcher("/orderEdit.jsp").forward(request, response);
	}

	private void updateOrder(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException {

		String oriorderid = request.getParameter("oriorderid");
		String order = request.getParameter("order");
		String side = request.getParameter("side");
		String drink = request.getParameter("drink");
		String payment = request.getParameter("payment");
		String orderid = request.getParameter("orderid");
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(UPDATE_ORDER_SQL);) {
			statement.setString(1, order);
			statement.setString(2, side);
			statement.setString(3, drink);
			statement.setString(4, payment);
			statement.setString(5, orderid);
			statement.setString(6, oriorderid);
			int i = statement.executeUpdate();

		}
		response.sendRedirect(
				"http://localhost:8090/22053365-SamanthaTeo-DV1C01-AY2021S3-GradedAssignment/OrderServlet/dashboard");

	}

	private void deleteOrder(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException {

		String orderid = request.getParameter("orderid");

		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(DELETE_ORDER_SQL);) {
			statement.setString(1, orderid);
			int i = statement.executeUpdate();
		}

		response.sendRedirect(
				"http://localhost:8090/22053365-SamanthaTeo-DV1C01-AY2021S3-GradedAssignment/OrderServlet/dashboard");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		String m = request.getParameter("order");
		String s = request.getParameter("side");
		String d = request.getParameter("drink");
		String p = request.getParameter("payment");
		String o = request.getParameter("orderid");

		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/menu", "root", "password");

			PreparedStatement ps = con.prepareStatement("insert into menu values(?,?,?,?,?)");
			ps.setString(1, m);
			ps.setString(2, s);
			ps.setString(3, d);
			ps.setString(4, p);
			ps.setString(5, o);

			int i = ps.executeUpdate();

			if (i > 0) {
				PrintWriter writer = response.getWriter();
				writer.println("<h1>" + "Order Successful!" + "</h1>");
				writer.close();
			}
		}

		catch (Exception exception) {
			System.out.println(exception);
			out.close();
		}
		doGet(request, response);
		
	}
	private void listOrder(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		List<Order> order = new ArrayList<>();
		try (Connection connection = getConnection();
				
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_ORDER);) {
			
			ResultSet rs = preparedStatement.executeQuery();
			
			while (rs.next()) {
			
				String order1 = rs.getString("order");
				String side = rs.getString("side");
				String drink = rs.getString("drink");
				String payment = rs.getString("payment");
				String orderid = rs.getString("orderid");
				order.add(new Order(order1, side, drink, payment, orderid));
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

		request.setAttribute("listOrder", order);
		request.getRequestDispatcher("/userManagement.jsp").forward(

				request, response);
	}

}
