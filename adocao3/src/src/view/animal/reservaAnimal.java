package src.view.animal;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import banco.animalBanco;

public class reservaAnimal {

    protected Shell shell;
    private Text textIdAnimal;
    private animalBanco animalBanco;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        try {
            reservaAnimal window = new reservaAnimal();
            window.open();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public reservaAnimal() {
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
        shell.setSize(424, 330);
        shell.setText("Reservar Animal");

        // Definir imagem de fundo
        Image backgroundImage = new Image(Display.getDefault(), "C:/Users/zelio/Downloads/adocaoJavaatt6/fotos/petAd.jpg");
        shell.setBackgroundImage(backgroundImage);

        // Título da janela
        Label lblTitulo = new Label(shell, SWT.NONE);
        lblTitulo.setFont(org.eclipse.wb.swt.SWTResourceManager.getFont("Segoe UI", 14, SWT.BOLD));
        lblTitulo.setText("Reserva do Animal");
        lblTitulo.setBounds(110, 10, 200, 25);
        lblTitulo.setBackground(shell.getBackground());

        // Label para ID do Animal
        Label lblIdAnimal = new Label(shell, SWT.NONE);
        lblIdAnimal.setBounds(112, 125, 72, 20);
        lblIdAnimal.setText("ID do Animal:");

        // Campo de entrada para o ID do Animal
        textIdAnimal = new Text(shell, SWT.BORDER);
        textIdAnimal.setBounds(190, 125, 120, 25);

        // Botão para Reservar o Animal
        Button btnReservar = new Button(shell, SWT.NONE);
        btnReservar.setBounds(266, 238, 120, 30);
        btnReservar.setText("Reservar");

        // Label para exibir o resultado da operação
        Label lblResultado = new Label(shell, SWT.NONE);
        lblResultado.setBounds(50, 187, 300, 25);

        // Ação de clique no botão Reservar
        btnReservar.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                int idAnimal;
                try {
                    // Lê o ID do Animal
                    idAnimal = Integer.parseInt(textIdAnimal.getText());
                    
                    // Chama o método reservaAnimal do banco
                    boolean sucesso = animalBanco.reservaAnimal(idAnimal);
                    
                    // Exibe o resultado na tela
                    if (sucesso) {
                        lblResultado.setText("Animal reservado com sucesso!");
                    } else {
                        lblResultado.setText("Falha: Animal não disponível ou inexistente.");
                    }
                } catch (NumberFormatException ex) {
                    lblResultado.setText("Erro: ID inválido.");
                }
            }
        });

        // Botão para fechar a janela
        Button btnFechar = new Button(shell, SWT.NONE);
        btnFechar.setBounds(21, 238, 120, 30);
        btnFechar.setText("Fechar");

        // Ação de clique no botão Fechar
        btnFechar.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                shell.dispose(); // Fecha a janela
            }
        });
    }
}
