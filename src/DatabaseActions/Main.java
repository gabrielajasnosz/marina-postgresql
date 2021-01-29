package DatabaseActions;

import DatabaseActions.Client;
import DatabaseActions.ClientTableModel;

import static DatabaseActions.Client.*;

import java.sql.SQLException;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;


public class Main {

    static final String JDBC_DRIVER = "org.postgresql.Driver";
    static final String DB_URL = "jdbc:postgresql://195.150.230.210:5434/";
    static final String USER = "2020_jasnosz_gabriela";
    static final String PASS = "32000";


    public static void returnTable(List<Client> clientsList) {
        SwingUtilities.invokeLater(() -> {
            ClientTableModel model = new ClientTableModel(clientsList);
            JTable table = new JTable(model);

            JFrame frame = new JFrame();
            frame.add(new JScrollPane(table));
            frame.setLocation(0, 0);

            frame.setTitle("Clients table:");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.pack();
            frame.setVisible(true);

        });

    }


    public static void main(String[] args) {
        List<Client> clientsList = null;
        try {
            clientsList = getClients();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        returnTable(clientsList);


    }


}


//            case 11: {
//                // Przykład #11
//                // Odczyt danych z tabeli z użyciem Statement (SENSITIVE)
//                SwingUtilities.invokeLater(new Runnable() {
//                    @Override
//                    public void run() {
//                        // @TP Laboratory(description, windowRelativePosition)
//                        List<Client> employeeList = getEmployees_StatementSensitive();
//                        Laboratory tableT1 = new Laboratory("Transaction T1", null, employeeList);
//                    }
//                });
//                break;
//            }
//            case 12: {
//                // Przykład #12 :: Generyczny dostęp do danych, be znajomości strktury tabeli.
//                try {
//                    System.out.println(getEmployees().displayHTML("University employees"));
//                } catch (Exception e) {
//                    System.err.println("Error. Exception: " + e);
//                }
//                break;
//            }
//            case 13: {
//                // Przykład #13 :: Dostęp do danych z pełną kontrolą via Statement. Rezultat jest pojedynczą wartością.
//                System.out.println("Salary for a given employee ID is " + getEmployeeSalary_Statement(1));
//                break;
//            }
//
//            case 20: {
//                // Przykład #20 :: Dostęp do danych z pełną kontrolą via PreparedStatement. Rezultat jest pojedynczą wartością.
//                System.out.println("Salary for a given employee ID is " + getEmployeeSalary_PreparedStatement(1));
//                break;
//            }
//            case 21: {
//                // Przykład #21 :: Dostęp do danych z pełną kontrolą via PreparedStatement. Rezultat jest kolekcją, w tym przypadku zbiorem wartości.
//                System.out.println("Salary for employees with ID between LOWER_BOUND and UPER_BOUND.");
//                List<Client> employeeList = getEmployeesSalary_PreparedStatementResultSet(4, 7);
//                for (int row = 0; row < employeeList.size(); row++) {
//                    System.out.println("Salary for an employee ID = " + employeeList.get(row).getId() + " is " + employeeList.get(row).getSalary());
//                }
//                break;
//            }
//            case 22: {
//                // Przykład #22 :: Zmiana danych z pełną kontrolą via PreparedStatement.
//                // Zmiana danych jest wykonywana poprzez UPDATE. Dodatkowo zmienione dane są odczytywane do ResultSet. Zmiana danych następuje z wykorzystaniem executeQuery().
//                System.out.println("Modified salary for a given employee ID is now " + changeEmployeeSalary_PreparedStatement(0.1, 1));
//                break;
//            }
//            case 23: {
//                // Przykład #23 :: Zmiana danych z pełną kontrolą via PreparedStatement i ResultSet.
//                // Zmiana danych jest wykonywana poprzez SELECT. Zmienione dane są odczytywane do ResultSet i w ResultSet zmieniane. Zmiana danych następuje z wykorzystaniem updateRow().
//                System.out.println("Modified salary for a given employee ID is now " + changeEmployeeSalary_PreparedStatementResultSet(0.1, 1));
//                break;
//            }
//            case 24: {
//                // Przykład #24 :: Zmiana danych z pełną kontrolą via PreparedStatement z wykorzystaniem executeUpdate().
//                // Zmiana danych jest wykonywana poprzez UPDATE. Zmiana danych następuje z wykorzystaniem executeUpdate().
//                System.out.println("Salary was modified for " + changeEmployeeSalary_PreparedStatementViaExecuteUpdate(0.1, 2000) + " employees.");
//                break;
//            }
//            case 25: {
//                // Przykład #25 :: Zmiana danych z pełną kontrolą via PreparedStatement. Problem trybu AUTCOMMIT = TRUE.
//                // Zmiana danych jest wykonywana poprzez UPDATE. Dodatkowo zmienione dane są odczytywane do ResultSet.
//                // Metoda powinna dokonać zmiany wynagrodzenia tylko w przypadku, gdy zmienione wynagrodzenie nie przekroczy limitu 10 000 PLN.
//                // Jakie jest wynagrodzenie pracownika ID = 54 po wykonaniu metody?
//                System.out.println("Modified salary for a given employee ID is now " + changeSalary_RollbackError(0.25, 54));
//                break;
//            }
//            case 26: {
//                // Przykład #26 :: Zmiana danych z pełną kontrolą via PreparedStatement. Zmiana danych odbywa się w trybie AUTOCOMMIT = FALSE.
//                // Zmiana danych jest wykonywana poprzez UPDATE. Dodatkowo zmienione dane są odczytywane do ResultSet.
//                // Metoda powinna dokonać zmiany wynagrodzenia tylko w przypadku, gdy zmienione wynagrodzenie nie przekroczy limitu 10 000 PLN.
//                double changedSalary = changeSalary_ExecuteQueryRollback(0.25, 54);
//                if(changedSalary == -1)
//                    System.out.println("The salary was not changed.");
//                else
//                    System.out.println("Modified salary for a given employee ID is now " + changedSalary);
//                break;
//            }
//            case 27: {
//                // Przykład #27 :: Kontrola nad zagnieżdżonymi transakcjami
//                long startTime = System.currentTimeMillis();
//                changeSalaryTwice_ExecuteQueryRollback(0.1, 1);
//                long endTime = System.currentTimeMillis();
//                System.out.println("Execution time: " + (endTime - startTime) + " ms");
//                break;
//            }
//            case 28: {
//                // Przykład #28 :: Dodanie danych via SELECT
//                addEmployeePayment_PreparedStatementResultSet(5555, 10, 2019, "wynagrodzenie miesięczne", 1);
//                break;
//            }
//            case 29: {
//                // Przykład #29 :: Działanie ResultSet typu Sensitive
//                break;
//            }
//
//            case 40: {
//                // Przykład #40
//                // Obserwacja widoczności zmiany wprowadzanych przez metodę changeSalary_ExecuteQueryRollback()
//                SwingUtilities.invokeLater(new Runnable() {
//                    @Override
//                    public void run() {
//                        // @TP Laboratory(description, windowRelativePosition)
//                        List<Client> employeeList1 = getEmployees_Statement();
//                        Laboratory tableT1 = new Laboratory("Transaction T1", null, employeeList1);
//                        changeSalary_ExecuteQueryRollback(0.25, 20);
//                        List<Client> employeeList2 = getEmployees_Statement();
//                        Laboratory tableT2 = new Laboratory("Transaction T2", tableT1, employeeList2);
//                        changeSalary_ExecuteQueryRollback(0.25, 20);
//                        List<Client> employeeList3 = getEmployees_Statement();
//                        Laboratory tableT3 = new Laboratory("Transaction T3", tableT2, employeeList3);
//                    }
//                });
//                break;
//            }
//
//            case 50: {
//                // Przykład #50 :: Używanie puli połączeń
//                // Inicjalizacja puli.
//                ConnectionPool cp = new ConnectionPool();
//                try {
//                    Class jdbc = Class.forName(JDBC_DRIVER);
//                    Driver driver = DriverManager.getDriver(DB_URL);
//                    System.out.println("Information. JDBC driver loaded " + jdbc.getCanonicalName() + " / JDBC version: " + driver.getMajorVersion() + "." + driver.getMinorVersion());
//                    try {
//                        cp.setDriver(JDBC_DRIVER);
//                        cp.setURL(DB_URL);
//                        cp.setUsername(USER);
//                        cp.setPassword(PASS);
//                        cp.setSize(POOL_SIZE);
//                        cp.initializePool();
//                        System.out.println("Information. Connection pool size " + POOL_SIZE + " on " + DB_URL + " was initialized.");
//                    } catch (Exception e) {
//                        System.err.println("Error. Pool initialization error. " + e);
//                    }
//                } catch (Exception e) {
//                    System.err.println("Error. JDBC loading error. " + e);
//                }
//
//                // Użycie połączeń z puli.
//                java.sql.Connection con1 = null;
//                java.sql.Connection con2 = null;
//                try {
//                    con1 = cp.getConnection();
//                    con2 = cp.getConnection();
//                    // Obsługa transakcji
//                } catch (Exception e) {
//                    System.err.println("Error. Getting connection. " + e);
//                } finally {
//                    cp.releaseConnection(con1);
//                    cp.releaseConnection(con2);
//                }
//
//                // Zamknięcie puli.
//                cp.emptyPool();
//                break;
//            }
//            default: {
//                System.out.println("No choice has been made.");
//            }