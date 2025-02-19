package src.view.animal;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.RGB;

import banco.animalBanco;
import model.Animal;

import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Label;
import org.eclipse.jface.resource.LocalResourceManager;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.resource.FontDescriptor;
import org.eclipse.swt.graphics.Image;


public class alterarAnimal {

    protected Shell shell;
    private Text textidAnimal;
    private Text textnomeAnimal;
    private Text textsexoAnimal;
    private Text textracaAnimal;
    private animalBanco animalbanco;
    private LocalResourceManager localResourceManager;

    /**
     * Launch the application.
     * @param args
     */
    public static void main(String[] args) {
        try {
            alterarAnimal window = new alterarAnimal();
            window.open();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public alterarAnimal() {
        animalbanco = new animalBanco();
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
        createResourceManager();
        shell.setSize(594, 431);
        shell.setText("Alterar Animal");

        // Configura o fundo do Shell com a imagem
        Image backgroundImage = new Image(shell.getDisplay(), "C:/Users/zelio/Downloads/adocaoJavaatt6/fotos/petsAn.png");
        shell.setBackgroundImage(backgroundImage);

        textidAnimal = new Text(shell, SWT.BORDER);
        textidAnimal.setBounds(355, 73, 83, 20);

        textnomeAnimal = new Text(shell, SWT.BORDER);
        textnomeAnimal.setBounds(36, 179, 241, 30);

        textsexoAnimal = new Text(shell, SWT.BORDER);
        textsexoAnimal.setBounds(36, 215, 241, 30);

        textracaAnimal = new Text(shell, SWT.BORDER);
        textracaAnimal.setBounds(36, 251, 241, 30);

        Button btnCancelar = new Button(shell, SWT.NONE);
        btnCancelar.setText("Cancelar");
        btnCancelar.setForeground(localResourceManager.createColor(new RGB(255, 255, 255)));
        btnCancelar.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_RED));
        btnCancelar.setBounds(143, 331, 97, 34);

        Button btnOK = new Button(shell, SWT.NONE);
        btnOK.setText("OK");
        btnOK.setForeground(localResourceManager.createColor(new RGB(255, 255, 255)));
        btnOK.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_DARK_GREEN));
        btnOK.setBounds(386, 331, 97, 34);

        Label lblRacaAnimal = new Label(shell, SWT.NONE);
        lblRacaAnimal.setBounds(302, 254, 97, 15);
        lblRacaAnimal.setText("Raça do Animal");

        Label lblSexoAnimal = new Label(shell, SWT.NONE);
        lblSexoAnimal.setBounds(302, 218, 97, 15);
        lblSexoAnimal.setText("Sexo do Animal");

        Label lblNomeAnimal = new Label(shell, SWT.NONE);
        lblNomeAnimal.setBounds(302, 182, 97, 15);
        lblNomeAnimal.setText("Nome do Animal");

        Label lblInsiraNovosDados = new Label(shell, SWT.NONE);
        lblInsiraNovosDados.setBounds(36, 143, 121, 15);
        lblInsiraNovosDados.setText("Insira os novos dados:");

        Label lblIdAnimal = new Label(shell, SWT.NONE);
        lblIdAnimal.setBounds(128, 76, 215, 20);
        lblIdAnimal.setText("Digite o ID do animal que deseja alterar:");

        Label lblAlterarDados = new Label(shell, SWT.NONE);
        lblAlterarDados.setFont(localResourceManager.create(FontDescriptor.createFrom("@Microsoft JhengHei UI", 14, SWT.NORMAL)));
        lblAlterarDados.setBounds(164, 10, 254, 30);
        lblAlterarDados.setText("Alterar os Dados do Animal");
        lblAlterarDados.setBackground(shell.getBackground()); // Remove o fundo

        btnOK.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                Animal animal = new Animal();
                int idAnimal = Integer.parseInt(textidAnimal.getText());
                animal.setIdAnimal(idAnimal);
                animal.setNomeAnimal(textnomeAnimal.getText());
                animal.setSexoAnimal(textsexoAnimal.getText());
                animal.setRacaAnimal(textracaAnimal.getText());

                animalbanco.alterarAnimal(animal);

                MessageBox messagebox = new MessageBox(shell, SWT.ICON_INFORMATION | SWT.OK);
                messagebox.setMessage("Animal alterado com sucesso");
                messagebox.open();
            }
        });

        btnCancelar.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                // Limpa os campos de texto
                textnomeAnimal.setText("");
                textsexoAnimal.setText("");
                textracaAnimal.setText("");

                // Fecha a janela ao cancelar
                shell.dispose();
            }
        });
    }

    private void createResourceManager() {
        localResourceManager = new LocalResourceManager(JFaceResources.getResources(), shell);
    }
}
