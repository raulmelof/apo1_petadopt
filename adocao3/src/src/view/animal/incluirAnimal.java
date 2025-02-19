package src.view.animal;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;

import banco.animalBanco;
import model.Animal;

public class incluirAnimal {

    protected Shell shell;
    private Text textnomeAnimal;
    private Text textsexoAnimal;
    private Text textracaAnimal;
    private animalBanco animalbanco;

    public static void main(String[] args) {
        try {
            incluirAnimal window = new incluirAnimal();
            window.open();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public incluirAnimal() {
        animalbanco = new animalBanco(); // Inicialize o objeto do banco aqui
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
        shell.setSize(500, 400);
        shell.setText("Inserir Animal");

        // Configurando plano de fundo
        Image backgroundImage = new Image(shell.getDisplay(), "C:/Users/zelio/Downloads/adocaoJavaatt6/fotos/petAd.jpg");
        shell.setBackgroundImage(backgroundImage);


        // Título
        Label lblTitulo = new Label(shell, SWT.NONE);
        lblTitulo.setFont(SWTResourceManager.getFont("Segoe UI", 14, SWT.BOLD));
        lblTitulo.setText("Cadastrar Novo Animal");
        lblTitulo.setBounds(150, 10, 206, 30);
        lblTitulo.setBackground(shell.getBackground());

        // Nome do Animal
        Label lblNomeAnimal = new Label(shell, SWT.NONE);
        lblNomeAnimal.setText("Nome do Animal:");
        lblNomeAnimal.setBounds(54, 129, 120, 20);
        lblNomeAnimal.setBackground(shell.getBackground());

        textnomeAnimal = new Text(shell, SWT.BORDER);
        textnomeAnimal.setBounds(180, 126, 220, 25);

        // Sexo do Animal
        Label lblSexoAnimal = new Label(shell, SWT.NONE);
        lblSexoAnimal.setText("Sexo do Animal:");
        lblSexoAnimal.setBounds(54, 173, 120, 20);
        lblSexoAnimal.setBackground(shell.getBackground());

        textsexoAnimal = new Text(shell, SWT.BORDER);
        textsexoAnimal.setBounds(180, 170, 220, 25);

        // Raça do Animal
        Label lblRacaAnimal = new Label(shell, SWT.NONE);
        lblRacaAnimal.setText("Raça do Animal:");
        lblRacaAnimal.setBounds(54, 215, 120, 20);
        lblRacaAnimal.setBackground(shell.getBackground());

        textracaAnimal = new Text(shell, SWT.BORDER);
        textracaAnimal.setBounds(180, 212, 220, 25);

        // Botão OK
        Button btnOK = new Button(shell, SWT.PUSH);
        btnOK.setBounds(300, 279, 100, 30);
        btnOK.setText("OK");

        btnOK.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                try {
                    Animal animal = new Animal();
                    animal.setNomeAnimal(textnomeAnimal.getText());
                    animal.setSexoAnimal(textsexoAnimal.getText());
                    animal.setRacaAnimal(textracaAnimal.getText());

                    animalbanco.incluirAnimal(animal);

                    MessageBox messagebox = new MessageBox(shell, SWT.ICON_INFORMATION | SWT.OK);
                    messagebox.setMessage("Animal inserido com sucesso!");
                    messagebox.open();

                    // Limpa os campos após inserção bem-sucedida
                    textnomeAnimal.setText("");
                    textsexoAnimal.setText("");
                    textracaAnimal.setText("");

                } catch (Exception ex) {
                    MessageBox messagebox = new MessageBox(shell, SWT.ICON_ERROR | SWT.OK);
                    messagebox.setMessage("Erro ao inserir o animal: " + ex.getMessage());
                    messagebox.open();
                }
            }
        });

        // Botão Cancelar
        Button btnCancelar = new Button(shell, SWT.PUSH);
        btnCancelar.setBounds(98, 279, 100, 30);
        btnCancelar.setText("Cancelar");

        btnCancelar.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                // Limpa os campos e fecha a janela
                textnomeAnimal.setText("");
                textsexoAnimal.setText("");
                textracaAnimal.setText("");
                shell.dispose();
            }
        });
    }
}
