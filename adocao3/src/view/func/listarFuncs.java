package view.func;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import banco.funcionarioBanco;
import model.Funcionarios;
import java.util.List;
import org.eclipse.jface.resource.LocalResourceManager;
import org.eclipse.swt.graphics.RGB;

public class listarFuncs {
    protected Shell shell;
    private Table table;
    private funcionarioBanco funcionarioBanco;
    private LocalResourceManager localResourceManager;

    public static void main(String[] args) {
        try {
            listarFuncs window = new listarFuncs();
            window.open();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public listarFuncs() {
        funcionarioBanco = new funcionarioBanco(); // Inicializa o objeto para interagir com o banco
    }

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

    protected void createContents() {
        shell = new Shell();
        shell.setSize(600, 400);
        shell.setText("Listar Funcionários");

        // Inicializando o localResourceManager
        localResourceManager = new LocalResourceManager(org.eclipse.jface.resource.JFaceResources.getResources(), shell);

        table = new Table(shell, SWT.BORDER | SWT.FULL_SELECTION);
        table.setBounds(10, 10, 560, 300);
        table.setHeaderVisible(true);
        table.setLinesVisible(true);

        // Colunas da Tabela
        TableColumn tblclmnId = new TableColumn(table, SWT.NONE);
        tblclmnId.setWidth(100);
        tblclmnId.setText("ID");

        TableColumn tblclmnNome = new TableColumn(table, SWT.NONE);
        tblclmnNome.setWidth(200);
        tblclmnNome.setText("Nome");

        TableColumn tblclmnDep = new TableColumn(table, SWT.NONE);
        tblclmnDep.setWidth(200);
        tblclmnDep.setText("Departamento");

        // Botão para listar funcionários
        Button btnListar = new Button(shell, SWT.NONE);
        btnListar.setForeground(localResourceManager.createColor(new RGB(255, 255, 255)));
        btnListar.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_DARK_GREEN));
        btnListar.setBounds(441, 320, 120, 30);
        btnListar.setText("Listar Funcionários");

        btnListar.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                // Limpa a tabela antes de adicionar novos itens
                table.removeAll();

                // Chama o método para listar todos os funcionários
                List<Funcionarios> funcionarios = funcionarioBanco.listarFuncs();

                // Verifica se a lista não está vazia
                if (funcionarios != null && !funcionarios.isEmpty()) {
                    // Adiciona os resultados na tabela
                    for (Funcionarios func : funcionarios) {
                        TableItem item = new TableItem(table, SWT.NONE);
                        item.setText(new String[] {
                            String.valueOf(func.getIdFuncionario()),
                            func.getNomeFuncionario(),
                            func.getDepFuncionario()
                        });
                    }
                } else {
                    TableItem item = new TableItem(table, SWT.NONE);
                    item.setText("Nenhum funcionário encontrado.");
                }
            }
        });

        // Botão para fechar a janela
        Button btnFechar = new Button(shell, SWT.NONE);
        btnFechar.setForeground(localResourceManager.createColor(new RGB(255, 255, 255)));
        btnFechar.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_RED));
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
