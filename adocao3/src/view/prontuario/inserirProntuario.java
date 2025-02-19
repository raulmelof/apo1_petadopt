package view.prontuario;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.jface.resource.LocalResourceManager;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.graphics.RGB;

import banco.prontuarioBanco;
import model.Prontuario;

public class inserirProntuario {
    protected Shell shell;
    private Text txtIdAnimal;
    private Text txtIdadeAnimal;
    private Text txtPesoAnimal;
    private Text txtHigieneAnimal;
    private Text txtTipoAnimal;
    private LocalResourceManager localResourceManager;
    private prontuarioBanco prontuarioBanco;

    public static void main(String[] args) {
        try {
            inserirProntuario window = new inserirProntuario();
            window.open();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public inserirProntuario() {
        prontuarioBanco = new prontuarioBanco(); // Inicializa o banco
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
        createResourceManager();
        shell.setSize(500, 350);
        shell.setText("Inserir Prontuário");

        // Definir tamanho mínimo e máximo da janela
        shell.setMinimumSize(500, 350);
        shell.setMaximumSize(500, 350);

        // Definir imagem de fundo
        Image backgroundImage = new Image(Display.getCurrent(), "C:/Users/zelio/Downloads/adocaoJavaatt6/fotos/petsPront.jpg");
        shell.setBackgroundImage(backgroundImage);
        shell.setBackgroundMode(SWT.INHERIT_FORCE);

        // Título
        Label lblTitulo = new Label(shell, SWT.CENTER);
        lblTitulo.setText("Inserir Prontuário de Animal");
        lblTitulo.setFont(JFaceResources.getFontRegistry().getBold(JFaceResources.DEFAULT_FONT));
        lblTitulo.setBounds(150, 10, 200, 20);

        // Campo: ID do Animal
        Label lblIdAnimal = new Label(shell, SWT.NONE);
        lblIdAnimal.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_WHITE));
        lblIdAnimal.setText("ID do Animal:");
        lblIdAnimal.setBounds(10, 50, 120, 20);

        txtIdAnimal = new Text(shell, SWT.BORDER);
        txtIdAnimal.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_WHITE));
        txtIdAnimal.setBounds(140, 50, 200, 20);

        // Campo: Idade do Animal
        Label lblIdadeAnimal = new Label(shell, SWT.NONE);
        lblIdadeAnimal.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_WHITE));
        lblIdadeAnimal.setText("Idade do Animal:");
        lblIdadeAnimal.setBounds(10, 90, 120, 20);

        txtIdadeAnimal = new Text(shell, SWT.BORDER);
        txtIdadeAnimal.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_WHITE));
        txtIdadeAnimal.setBounds(140, 90, 200, 20);

        // Campo: Peso do Animal
        Label lblPesoAnimal = new Label(shell, SWT.NONE);
        lblPesoAnimal.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_WHITE));
        lblPesoAnimal.setText("Peso do Animal:");
        lblPesoAnimal.setBounds(10, 130, 120, 20);

        txtPesoAnimal = new Text(shell, SWT.BORDER);
        txtPesoAnimal.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_WHITE));
        txtPesoAnimal.setBounds(140, 130, 200, 20);

        // Campo: Higiene do Animal
        Label lblHigieneAnimal = new Label(shell, SWT.NONE);
        lblHigieneAnimal.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_WHITE));
        lblHigieneAnimal.setText("Higiene do Animal:");
        lblHigieneAnimal.setBounds(10, 170, 120, 20);

        txtHigieneAnimal = new Text(shell, SWT.BORDER);
        txtHigieneAnimal.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_WHITE));
        txtHigieneAnimal.setBounds(140, 170, 200, 20);

        // Campo: Tipo do Animal
        Label lblTipoAnimal = new Label(shell, SWT.NONE);
        lblTipoAnimal.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_WHITE));
        lblTipoAnimal.setText("Tipo do Animal:");
        lblTipoAnimal.setBounds(10, 210, 120, 20);

        txtTipoAnimal = new Text(shell, SWT.BORDER);
        txtTipoAnimal.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_WHITE));
        txtTipoAnimal.setBounds(140, 210, 200, 20);

        // Botão Inserir
        Button btnInserir = new Button(shell, SWT.PUSH);
        btnInserir.setText("Inserir");
        btnInserir.setForeground(localResourceManager.createColor(new RGB(255, 255, 255)));
        btnInserir.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_DARK_GREEN));
        btnInserir.setBounds(330, 250, 100, 30);

        // Botão Cancelar
        Button btnCancelar = new Button(shell, SWT.PUSH);
        btnCancelar.setText("Cancelar");
        btnCancelar.setForeground(localResourceManager.createColor(new RGB(255, 255, 255)));
        btnCancelar.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_RED));
        btnCancelar.setBounds(10, 250, 100, 30);

        // Evento para o botão "Inserir"
        btnInserir.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                handleInserir();
            }
        });

        // Evento para o botão "Cancelar"
        btnCancelar.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                shell.dispose();
            }
        });
    }

    private void createResourceManager() {
        localResourceManager = new LocalResourceManager(JFaceResources.getResources(), shell);
    }

    private void handleInserir() {
        try {
            int idAnimal = Integer.parseInt(txtIdAnimal.getText());

            // Verifica se o ID do Animal existe no banco
            if (!prontuarioBanco.verificaIdExiste(idAnimal)) {
                throw new Exception("ID do animal não encontrado no banco de dados.");
            }

            Prontuario prontuario = new Prontuario();
            prontuario.setIdAnimal(idAnimal);
            prontuario.setIdadeAnimal(txtIdadeAnimal.getText());
            prontuario.setPesoAnimal(Float.parseFloat(txtPesoAnimal.getText()));
            prontuario.setHigieneAnimal(txtHigieneAnimal.getText());
            prontuario.setTipoAnimal(txtTipoAnimal.getText());

            prontuarioBanco.inserirProntuario(prontuario);

            MessageBox messageBox = new MessageBox(shell, SWT.ICON_INFORMATION | SWT.OK);
            messageBox.setMessage("Prontuário inserido com sucesso!");
            messageBox.open();
        } catch (NumberFormatException ex) {
            showError("Erro: Certifique-se de que os campos numéricos são válidos.");
        } catch (Exception ex) {
            showError("Erro ao inserir prontuário: " + ex.getMessage());
        }
    }

    private void showError(String message) {
        MessageBox messageBox = new MessageBox(shell, SWT.ICON_ERROR | SWT.OK);
        messageBox.setMessage(message);
        messageBox.open();
    }
}
