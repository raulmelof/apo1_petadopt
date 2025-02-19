package view.cliente;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import banco.clienteBanco;
import model.Cliente;
import java.util.List;

public class listarClientes {
    protected Shell shell;
    private Table table;
    private clienteBanco clienteBanco;

    /**
     * Launch the application.
     * @param args
     */
    public static void main(String[] args) {
        try {
            listarClientes window = new listarClientes();
            window.open();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public listarClientes() {
        clienteBanco = new clienteBanco(); // Inicializa o objeto para interagir com o banco
    }

    /**
     * Open the window.
     */
    public void open() {
        Display display = Display.getDefault();
        createContents();
        shell.open();
        shell.layout();
        while (!shell.isDisposed()) {
            if (!display.readAndDispatch()) {
                display.sleep();
            }
        }
    }

    /**
     * Create contents of the window.
     */
    protected void createContents() {
        shell = new Shell();
        shell.setSize(662, 400);
        shell.setText("Listar Clientes");

        table = new Table(shell, SWT.BORDER | SWT.FULL_SELECTION);
        table.setBounds(10, 10, 626, 300);
        table.setHeaderVisible(true);
        table.setLinesVisible(true);

        // Colunas da Tabela
        TableColumn tblclmnCpf = new TableColumn(table, SWT.NONE);
        tblclmnCpf.setWidth(150);
        tblclmnCpf.setText("CPF");

        TableColumn tblclmnNome = new TableColumn(table, SWT.NONE);
        tblclmnNome.setWidth(200);
        tblclmnNome.setText("Nome");

        TableColumn tblclmnTel = new TableColumn(table, SWT.NONE);
        tblclmnTel.setWidth(150);
        tblclmnTel.setText("Telefone");

        TableColumn tblclmnEndereco = new TableColumn(table, SWT.NONE);
        tblclmnEndereco.setWidth(200);
        tblclmnEndereco.setText("Endereço");
        
        TableColumn tblclmnStatus = new TableColumn(table, SWT.NONE); // Nova coluna
        tblclmnStatus.setWidth(100);
        tblclmnStatus.setText("Status Cliente");


        // Botão para listar clientes
        Button btnListar = new Button(shell, SWT.NONE);
        btnListar.setBounds(498, 320, 120, 30);
        btnListar.setText("Listar Clientes");

        btnListar.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                // Limpa a tabela antes de adicionar novos itens
                table.removeAll();

                // Chama o método para listar todos os clientes
                List<Cliente> clientes = clienteBanco.listarClientes();

                // Adiciona os resultados na tabela
                for (Cliente cliente : clientes) {
                    TableItem item = new TableItem(table, SWT.NONE);
                    item.setText(new String[] {
                        cliente.getCpfCliente(),
                        cliente.getNomeCliente(),
                        cliente.getTelCliente(),
                        cliente.getEnderecoCliente(),
                        cliente.getStatus().toString()
                    });
                }
            }
        });

        // Botão para fechar a janela
        Button btnFechar = new Button(shell, SWT.NONE);
        btnFechar.setBounds(20, 320, 120, 30);
        btnFechar.setText("Fechar");

        btnFechar.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                shell.dispose(); // Fecha a janela
            }
        });
    }
}
