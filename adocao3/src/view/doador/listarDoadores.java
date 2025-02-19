package view.doador;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import banco.doadorBanco;
import model.Doador;
import java.util.List;

public class listarDoadores {
    protected Shell shell;
    private Table table;
    private doadorBanco doadorBanco;

    /**
     * Launch the application.
     * @param args
     */
    public static void main(String[] args) {
        try {
            listarDoadores window = new listarDoadores();
            window.open();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public listarDoadores() {
        doadorBanco = new doadorBanco(); // Inicializa o objeto para interagir com o banco
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
        shell.setSize(600, 450);
        shell.setText("Listar Doadores");

        table = new Table(shell, SWT.BORDER | SWT.FULL_SELECTION);
        table.setBounds(10, 10, 560, 355);
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

        // Botão para listar doadores
        Button btnListar = new Button(shell, SWT.NONE);
        btnListar.setBounds(439, 371, 120, 30);
        
        btnListar.setText("Listar Doadores");

        btnListar.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                // Limpa a tabela antes de adicionar novos itens
                table.removeAll();

                // Chama o método para listar todos os doadores
                List<Doador> doadores = doadorBanco.listarDoadores();

                // Adiciona os resultados na tabela
                for (Doador doador : doadores) {
                    TableItem item = new TableItem(table, SWT.NONE);
                    item.setText(new String[] {
                        doador.getCpfDoador(),
                        doador.getNomeDoador(),
                        doador.getTelDoador(),
                        doador.getEnderecoDoador()
                    });
                }
            }
        });

        // Botão para fechar a janela
        Button btnFechar = new Button(shell, SWT.NONE);
        btnFechar.setBounds(20, 371, 120, 30);
        btnFechar.setText("Fechar");

        btnFechar.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                shell.dispose(); // Fecha a janela
            }
        });
    }
}
