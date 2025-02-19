package view.vet;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.SWT;

import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

import banco.vetBanco;
import model.Veterinario;

import org.eclipse.jface.resource.LocalResourceManager;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.resource.FontDescriptor;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;

public class inserirCrmv {

    protected Shell shell;
    private Text txtIdFuncionario;
    private Text txtCrmvVeterinario;
    private vetBanco vetBanco;
    private Label lblInsirirCrmvDo;
    private LocalResourceManager localResourceManager;

    public static void main(String[] args) {
        try {
            inserirCrmv window = new inserirCrmv();
            window.open();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public inserirCrmv() {
        vetBanco = new vetBanco(); // Instancia o banco de dados
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
        shell.setSize(450, 300);
        shell.setText("Inserir CRMV do Veterinário");

        // Carregar a imagem de fundo
        Image backgroundImage = new Image(shell.getDisplay(), "C:/Users/zelio/Downloads/adocaoJavaatt6/fotos/petsVet.jpg");

        // Listener para desenhar a imagem no fundo
        shell.addPaintListener(new PaintListener() {
            @Override
            public void paintControl(PaintEvent e) {
                e.gc.drawImage(backgroundImage, 0, 0,
                    backgroundImage.getBounds().width, backgroundImage.getBounds().height,
                    0, 0, shell.getSize().x, shell.getSize().y);
            }
        });

        // Labels e campos de entrada de dados
        Label lblIdFuncionario = new Label(shell, SWT.NONE);
        lblIdFuncionario.setText("ID do Funcionário:");
        lblIdFuncionario.setBounds(81, 91, 97, 20);

        txtIdFuncionario = new Text(shell, SWT.BORDER);
        txtIdFuncionario.setBounds(184, 88, 168, 30);

        Label lblCrmvVeterinario = new Label(shell, SWT.NONE);
        lblCrmvVeterinario.setText("CRMV do Veterinário:");
        lblCrmvVeterinario.setBounds(60, 127, 118, 20);

        txtCrmvVeterinario = new Text(shell, SWT.BORDER);
        txtCrmvVeterinario.setBounds(184, 124, 168, 30);

        Button btnInserir = new Button(shell, SWT.NONE);
        btnInserir.setBounds(138, 221, 168, 30);
        btnInserir.setForeground(localResourceManager.createColor(new RGB(255, 255, 255)));
        btnInserir.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_DARK_GREEN));
        btnInserir.setText("Inserir CRMV");

        lblInsirirCrmvDo = new Label(shell, SWT.NONE);
        lblInsirirCrmvDo.setFont(localResourceManager.create(FontDescriptor.createFrom("@Microsoft JhengHei UI", 14, SWT.NORMAL)));
        lblInsirirCrmvDo.setBounds(100, 10, 252, 20);
        lblInsirirCrmvDo.setText("Inserir CRMV do Veterinário");

        btnInserir.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                try {
                    Integer idFuncionario = Integer.parseInt(txtIdFuncionario.getText());
                    String crmvVeterinario = txtCrmvVeterinario.getText();

                    Veterinario veterinario = new Veterinario(idFuncionario, crmvVeterinario);
                    // Chama o método para inserir o CRMV
                    vetBanco.inserirCrmv(veterinario);

                    // Mensagem de sucesso
                    MessageBox messagebox = new MessageBox(shell, SWT.ICON_INFORMATION | SWT.OK);
                    messagebox.setMessage("CRMV inserido com sucesso!");
                    messagebox.open();

                } catch (Exception ex) {
                    // Mensagem de erro em caso de falha
                    MessageBox messagebox = new MessageBox(shell, SWT.ICON_ERROR | SWT.OK);
                    messagebox.setMessage("Erro ao inserir o CRMV: " + ex.getMessage());
                    messagebox.open();
                }
            }
        });

        // Listener para liberar recursos da imagem
    }

    private void createResourceManager() {
        localResourceManager = new LocalResourceManager(JFaceResources.getResources(), shell);
    }
}
