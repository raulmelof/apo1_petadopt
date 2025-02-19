package src.view.animal;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

import banco.animalBanco;
import model.Animal;

import java.util.List;

public class listarAnimal {
    protected Shell shell;
    private Table table;
    private animalBanco animalBanco;  // Banco para interagir com os animais

    /**
     * Launch the application.
     * @param args
     */
    public static void main(String[] args) {
        try {
            listarAnimal window = new listarAnimal();
            window.open();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public listarAnimal() {
        animalBanco = new animalBanco(); // Inicializa o objeto para interagir com o banco
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
        shell.setSize(700, 450);
        shell.setText("Listar Todos os Animais");


        // Título estilizado
        Label lblTitulo = new Label(shell, SWT.NONE);
        lblTitulo.setFont(org.eclipse.wb.swt.SWTResourceManager.getFont("Segoe UI", 14, SWT.BOLD));
        lblTitulo.setText("Lista de Todos os Animais");
        lblTitulo.setBounds(250, 10, 300, 30);
        lblTitulo.setBackground(shell.getBackground());

        // Tabela para exibir os animais
        table = new Table(shell, SWT.BORDER | SWT.FULL_SELECTION);
        table.setBounds(10, 50, 660, 300);
        table.setHeaderVisible(true);
        table.setLinesVisible(true);

        // Colunas da tabela
        TableColumn tblclmnId = new TableColumn(table, SWT.NONE);
        tblclmnId.setWidth(50);
        tblclmnId.setText("ID");

        TableColumn tblclmnNome = new TableColumn(table, SWT.NONE);
        tblclmnNome.setWidth(200);
        tblclmnNome.setText("Nome");

        TableColumn tblclmnSexo = new TableColumn(table, SWT.NONE);
        tblclmnSexo.setWidth(100);
        tblclmnSexo.setText("Sexo");

        TableColumn tblclmnStatus = new TableColumn(table, SWT.NONE);
        tblclmnStatus.setWidth(200);
        tblclmnStatus.setText("Status");

        // Botão para listar os animais
        Button btnListar = new Button(shell, SWT.NONE);
        btnListar.setBounds(520, 371, 150, 30);
        btnListar.setText("Listar Todos os Animais");

        btnListar.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                try {
                    // Limpa a tabela antes de adicionar novos itens
                    table.removeAll();

                    // Chama o método para listar todos os animais
                    List<Animal> animais = animalBanco.listarAnimal();

                    if (animais.isEmpty()) {
                        // Exibe mensagem caso não haja animais cadastrados
                        MessageBox messagebox = new MessageBox(shell, SWT.ICON_INFORMATION | SWT.OK);
                        messagebox.setMessage("Nenhum animal encontrado.");
                        messagebox.open();
                    } else {
                        // Adiciona os resultados na tabela
                        for (Animal animal : animais) {
                            TableItem item = new TableItem(table, SWT.NONE);
                            item.setText(new String[]{
                                String.valueOf(animal.getIdAnimal()),
                                animal.getNomeAnimal(),
                                animal.getSexoAnimal(),
                                animal.getStatus().toString()
                            });
                        }
                    }
                } catch (Exception ex) {
                    // Exibe mensagem de erro caso ocorra uma falha na listagem
                    MessageBox messagebox = new MessageBox(shell, SWT.ICON_ERROR | SWT.OK);
                    messagebox.setMessage("Erro ao listar os animais: " + ex.getMessage());
                    messagebox.open();
                }
            }
        });

        // Botão para fechar a janela
        Button btnFechar = new Button(shell, SWT.NONE);
        btnFechar.setBounds(10, 371, 120, 30);
        btnFechar.setText("Fechar");

        btnFechar.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                shell.dispose(); // Fecha a janela
            }
        });
    }
}
