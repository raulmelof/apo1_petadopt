package view.doenca;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

import banco.doencaBanco;
import model.Doencas;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.jface.resource.LocalResourceManager;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.resource.FontDescriptor;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;

public class RemoverDoenca {
    protected Shell shell;
    private Text txtNomeDoenca;
    private Text txtCodProntuario;
    private doencaBanco doencaBanco;
    private Label lblRemoverDoenca;
    private LocalResourceManager localResourceManager;

    public static void main(String[] args) {
        try {
            RemoverDoenca window = new RemoverDoenca();
            window.open();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public RemoverDoenca() {
        doencaBanco = new doencaBanco(); // Inicializa o banco
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
        shell.setSize(640, 480);
        shell.setText("Remover Doença");

        // Carregar a imagem como fundo
        Image backgroundImage = new Image(shell.getDisplay(), "C:/Users/zelio/Downloads/adocaoJavaatt6/fotos/petsDoen.jpg");

        // Listener para desenhar a imagem como fundo
        shell.addPaintListener(new PaintListener() {
            @Override
            public void paintControl(PaintEvent e) {
                e.gc.drawImage(backgroundImage, 0, 0,
                    backgroundImage.getBounds().width, backgroundImage.getBounds().height,
                    0, 0, shell.getSize().x, shell.getSize().y);
            }
        });

        // Campo: Nome da Doença
        Label lblNomeDoenca = new Label(shell, SWT.NONE);
        lblNomeDoenca.setText("Nome da Doença:");
        lblNomeDoenca.setBounds(140, 173, 100, 15);

        txtNomeDoenca = new Text(shell, SWT.BORDER);
        txtNomeDoenca.setBounds(246, 170, 200, 20);

        // Campo: Código do Prontuário
        Label lblCodProntuario = new Label(shell, SWT.NONE);
        lblCodProntuario.setText("Código do Prontuário:");
        lblCodProntuario.setBounds(120, 199, 120, 20);

        txtCodProntuario = new Text(shell, SWT.BORDER);
        txtCodProntuario.setBounds(246, 196, 200, 20);

        // Botão Remover
        Button btnRemover = new Button(shell, SWT.NONE);
        btnRemover.setText("Remover");
        btnRemover.setBounds(427, 336, 100, 30);
        btnRemover.setForeground(localResourceManager.createColor(new RGB(255, 255, 255)));
        btnRemover.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_DARK_GREEN));

        lblRemoverDoenca = new Label(shell, SWT.NONE);
        lblRemoverDoenca.setFont(localResourceManager.create(FontDescriptor.createFrom("@Microsoft JhengHei UI", 14, SWT.NORMAL)));
        lblRemoverDoenca.setBounds(226, 42, 154, 30);
        lblRemoverDoenca.setText("Remover Doença");

        btnRemover.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                try {
                    String nomeDoenca = txtNomeDoenca.getText();
                    int codProntuario = Integer.parseInt(txtCodProntuario.getText());

                    if (nomeDoenca.isEmpty()) {
                        showError("O nome da doença não pode estar vazio.");
                        return;
                    }

                    Doencas doenca = new Doencas();
                    doenca.setNomeDoenca(nomeDoenca);
                    doenca.setCodProntuario(codProntuario);

                    doencaBanco.removerDoenca(doenca);

                    MessageBox messageBox = new MessageBox(shell, SWT.ICON_INFORMATION | SWT.OK);
                    messageBox.setMessage("Doença removida com sucesso!");
                    messageBox.open();
                } catch (NumberFormatException ex) {
                    showError("Erro: Certifique-se de que o campo Código do Prontuário seja um número válido.");
                } catch (Exception ex) {
                    showError("Erro ao remover doença: " + ex.getMessage());
                }
            }
        });

    }

    private void createResourceManager() {
        localResourceManager = new LocalResourceManager(JFaceResources.getResources(), shell);
    }

    private void showError(String message) {
        MessageBox messageBox = new MessageBox(shell, SWT.ICON_ERROR | SWT.OK);
        messageBox.setMessage(message);
        messageBox.open();
    }
}
