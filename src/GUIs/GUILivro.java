package GUIs;

import DAOs.DAOLivro;
import Entidades.*;
import javax.swing.WindowConstants;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import java.io.File;
import javax.swing.JFileChooser;
import java.awt.Image;
import javax.swing.JTextField;
import tools.*;
import DAOs.*;

public class GUILivro extends JFrame {

    ImageIcon iconeCreate = new ImageIcon(getClass().getResource("/icones/create.png"));
    ImageIcon iconeRetrieve = new ImageIcon(getClass().getResource("/icones/retrieve.png"));
    ImageIcon iconeUpdate = new ImageIcon(getClass().getResource("/icones/update.png"));
    ImageIcon iconeDelete = new ImageIcon(getClass().getResource("/icones/delete.png"));
    ImageIcon iconeSave = new ImageIcon(getClass().getResource("/icones/save.png"));
    ImageIcon iconeCancel = new ImageIcon(getClass().getResource("/icones/cancel.png"));
    ImageIcon iconeListar = new ImageIcon(getClass().getResource("/icones/list.png"));
    JButton btnCreate = new JButton(iconeCreate);
    JButton btnRetrieve = new JButton(iconeRetrieve);
    JButton btnUpdate = new JButton(iconeUpdate);
    JButton btnDelete = new JButton(iconeDelete);
    JButton btnSave = new JButton(iconeSave);
    JButton btnCancel = new JButton(iconeCancel);
    JButton btnList = new JButton(iconeListar);
    private JPanel pnNorte = new JPanel(new FlowLayout(FlowLayout.LEFT));
    private JPanel pnCentro = new JPanel(new GridLayout(6, 2));
    private JPanel pnSul = new JPanel(new GridLayout(1, 1));
    private JPanel pnE1 = new JPanel(new FlowLayout(FlowLayout.CENTER));
    private JPanel pnE2 = new JPanel(new FlowLayout(FlowLayout.CENTER));
    private JPanel pnE3 = new JPanel(new GridLayout(1, 1));
    private JLabel lbIdLivro = new JLabel("IdLivro");
    private JTextField tfIdLivro = new JTextField(10);
    private JLabel lbPreco = new JLabel("Preco");
    private JTextField tfPreco = new JTextField(10);
    private JLabel lbDescricao = new JLabel("Descricao");
    private JTextField tfDescricao = new JTextField(10);
    private JPanel pnVendaIdVenda = new JPanel(new GridLayout(1, 2));
    private JLabel lbVendaIdVenda = new JLabel("VendaIdVenda");
    private JTextField tfVendaIdVenda = new JTextField();
    private JButton btVendaIdVenda = new JButton("Procurar");
    private JPanel pnEditoraIdEditora = new JPanel(new GridLayout(1, 2));
    private JLabel lbEditoraIdEditora = new JLabel("EditoraIdEditora");
    private JTextField tfEditoraIdEditora = new JTextField();
    private JButton btEditoraIdEditora = new JButton("Procurar");
    private JPanel pnGeneroIdGenero = new JPanel(new GridLayout(1, 2));
    private JLabel lbGeneroIdGenero = new JLabel("GeneroIdGenero");
    private JTextField tfGeneroIdGenero = new JTextField();
    private JButton btGeneroIdGenero = new JButton("Procurar");
    JTextField tfCaminho = new JTextField();
    ScrollPane scroll = new ScrollPane();
    JTextArea jTextArea = new JTextArea();
    JPanel aviso = new JPanel();
    JLabel labelAviso = new JLabel("");
    String qualAcao = "";//variavel para facilitar insert e update
    DAOLivro daoLivro = new DAOLivro();
    Livro livro;
    private CaixaDeFerramentas ferramentas = new CaixaDeFerramentas();
    private JPanel pnEsquerda = new JPanel(new BorderLayout());
    private JPanel pnDireita = new JPanel(new BorderLayout());
    private JLabel rotulo = new JLabel();
    private JButton btAbrirImagem = new JButton("Selecionar imagem");
    private String caminho;
    private Image imagemAux;
    private ImageIcon icone;

    public GUILivro() {
        setSize(900, 400);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setTitle("CRUD - Livro");
        Container cp = getContentPane();
        cp = getContentPane();
        btnCreate.setToolTipText("Inserir novo registro");
        btnRetrieve.setToolTipText("Pesquisar por chave");
        btnUpdate.setToolTipText("Alterar");
        btnDelete.setToolTipText("Excluir");
        btnList.setToolTipText("Listar todos");
        btnSave.setToolTipText("Salvar");
        btnCancel.setToolTipText("Cancelar");
        cp.setLayout(new GridLayout(1, 2));
        cp.add(pnEsquerda);
        cp.add(pnDireita);
        try {
            String caminho = "";
            tfCaminho.setText(caminho);
            icone = new ImageIcon(getClass().getResource(caminho));
            imagemAux = icone.getImage();
            icone.setImage(imagemAux.getScaledInstance(300, 300, Image.SCALE_FAST));
            rotulo.setIcon(icone);
        } catch (Exception err) {
            System.out.println("erro " + err.getLocalizedMessage());
        }
        pnDireita.add(pnE1, BorderLayout.NORTH);
        pnE1.add(rotulo);
        pnDireita.add(pnE2, BorderLayout.CENTER);
        pnE2.add(btAbrirImagem);
        pnDireita.add(pnE3, BorderLayout.SOUTH);
        pnE3.add(tfCaminho);
        pnEsquerda.add(pnNorte, BorderLayout.NORTH);
        pnEsquerda.add(pnCentro, BorderLayout.CENTER);
        pnEsquerda.add(pnSul, BorderLayout.SOUTH);
        pnNorte.add(lbIdLivro);
        pnNorte.add(tfIdLivro);
        pnNorte.add(btnRetrieve);
        pnNorte.add(btnCreate);
        pnNorte.add(btnUpdate);
        pnNorte.add(btnDelete);
        pnNorte.add(btnSave);
        pnNorte.add(btnList);
        btnCancel.setVisible(false);
        btnDelete.setVisible(false);
        btnCreate.setVisible(false);
        btnSave.setVisible(false);
        btnUpdate.setVisible(false);
        btAbrirImagem.setEnabled(false);
        tfCaminho.setEditable(false);
        pnCentro.add(lbPreco);
        pnCentro.add(tfPreco);
        pnCentro.add(lbDescricao);
        pnCentro.add(tfDescricao);
        pnCentro.add(lbVendaIdVenda);
        pnCentro.add(pnVendaIdVenda);
        pnVendaIdVenda.add(tfVendaIdVenda);
        pnVendaIdVenda.add(btVendaIdVenda);
        pnCentro.add(lbEditoraIdEditora);
        pnCentro.add(pnEditoraIdEditora);
        pnEditoraIdEditora.add(tfEditoraIdEditora);
        pnEditoraIdEditora.add(btEditoraIdEditora);
        pnCentro.add(lbGeneroIdGenero);
        pnCentro.add(pnGeneroIdGenero);
        pnGeneroIdGenero.add(tfGeneroIdGenero);
        pnGeneroIdGenero.add(btGeneroIdGenero);
        pnSul.setBackground(Color.red);
        scroll.add(jTextArea);
        pnSul.add(scroll);
        tfCaminho.setEditable(false);
        tfPreco.setEditable(false);
        tfDescricao.setEditable(false);
        tfVendaIdVenda.setEditable(false);
        btVendaIdVenda.setEnabled(false);
        tfEditoraIdEditora.setEditable(false);
        btEditoraIdEditora.setEnabled(false);
        tfGeneroIdGenero.setEditable(false);
        btGeneroIdGenero.setEnabled(false);
        btnRetrieve.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    tfIdLivro.setBackground(Color.white);
                    jTextArea.setText("");
                    livro = new Livro();
                    int identificador = Integer.valueOf(tfIdLivro.getText());
                    livro.setIdLivro(identificador);
                    livro = daoLivro.obter(livro.getIdLivro());
                    if (livro == null) {
                        pnNorte.setBackground(Color.red);
                        tfCaminho.setText("");
                        tfPreco.setText("");
                        tfCaminho.setText("");
                        tfDescricao.setText("");
                        tfVendaIdVenda.setText("");
                        tfEditoraIdEditora.setText("");
                        tfGeneroIdGenero.setText("");
                        btnCreate.setVisible(true);
                    } else {
                        pnNorte.setBackground(Color.green);
                        caminho = livro.getCaminho();
                        tfCaminho.setText(caminho);
                        icone = new ImageIcon(caminho);
                        imagemAux = icone.getImage();
                        icone.setImage(imagemAux.getScaledInstance(300, 300, Image.SCALE_FAST));
                        rotulo.setIcon(icone);
                        tfPreco.setText(String.valueOf(livro.getPreco()));
                        tfCaminho.setText(livro.getCaminho());
                        tfDescricao.setText(livro.getDescricao());
                        String dao1 = String.valueOf(livro.getVendaIdVenda());
                        String[] aux1 = dao1.split("-");
                        tfVendaIdVenda.setText(aux1[0]);
                        String dao2 = String.valueOf(livro.getEditoraIdEditora());
                        String[] aux2 = dao2.split("-");
                        tfEditoraIdEditora.setText(aux2[0]);
                        String dao3 = String.valueOf(livro.getGeneroIdGenero());
                        String[] aux3 = dao3.split("-");
                        tfGeneroIdGenero.setText(aux3[0]);
                        btnUpdate.setVisible(true);
                        btnDelete.setVisible(true);
                        btnCreate.setVisible(false);
                    }
                    btAbrirImagem.setEnabled(false);
                    tfIdLivro.setEditable(false);
                    tfPreco.setEditable(false);
                    tfDescricao.setEditable(false);
                    btVendaIdVenda.setEnabled(false);
                    btEditoraIdEditora.setEnabled(false);
                    btGeneroIdGenero.setEnabled(false);
                    tfIdLivro.selectAll();
                } catch (Exception erro) {
                    pnNorte.setBackground(Color.yellow);
                    tfIdLivro.requestFocus();
                    tfIdLivro.setBackground(Color.red);
                    jTextArea.setText("Erro... \n");
                    jTextArea.append(erro.getMessage());
                }
            }
        });
        btnCreate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tfIdLivro.setEditable(false);
                tfPreco.requestFocus();
                btnCreate.setVisible(false);
                btnSave.setVisible(true);
                qualAcao = "incluir";
                livro = new Livro();
                tfPreco.setEditable(true);
                tfDescricao.setEditable(true);
                btVendaIdVenda.setEnabled(true);
                btEditoraIdEditora.setEnabled(true);
                btGeneroIdGenero.setEnabled(true);
                tfIdLivro.setEditable(false);
                btAbrirImagem.setEnabled(true);
            }
        });
        btnSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    jTextArea.setText("");
                    livro = new Livro();
                    livro.setIdLivro(Integer.valueOf(tfIdLivro.getText()));
                    livro.setPreco(Double.valueOf(tfPreco.getText()));
                    livro.setCaminho(tfCaminho.getText());
                    livro.setDescricao(tfDescricao.getText());
                    String[] aux0 = tfVendaIdVenda.getText().split("-");
                    DAOVenda daoVenda = new DAOVenda();
                    Venda d0 = daoVenda.obter(Integer.valueOf(aux0[0]));
                    livro.setVendaIdVenda(d0);
                    String[] aux1 = tfEditoraIdEditora.getText().split("-");
                    DAOEditora daoEditora = new DAOEditora();
                    Editora d1 = daoEditora.obter(Integer.valueOf(aux1[0]));
                    livro.setEditoraIdEditora(d1);
                    String[] aux2 = tfGeneroIdGenero.getText().split("-");
                    DAOGenero daoGenero = new DAOGenero();
                    Genero d2 = daoGenero.obter(Integer.valueOf(aux2[0]));
                    livro.setGeneroIdGenero(d2);
                    caminho = tfCaminho.getText();
                    livro.setCaminho(caminho);
                    caminho = "";
                    icone = new ImageIcon(caminho);
                    rotulo.setIcon(icone);
                    if (qualAcao.equals("incluir")) {
                        daoLivro.inserir(livro);
                    } else {
                        daoLivro.atualizar(livro);
                    }
                    tfIdLivro.setEditable(true);
                    tfIdLivro.requestFocus();
                    tfPreco.setText("");
                    tfCaminho.setText("");
                    tfDescricao.setText("");
                    tfVendaIdVenda.setText("");
                    tfEditoraIdEditora.setText("");
                    tfGeneroIdGenero.setText("");
                    tfCaminho.setText("");
                    btnSave.setVisible(false);
                    pnNorte.setBackground(Color.green);
                    tfPreco.setEditable(false);
                    tfCaminho.setEditable(false);
                    tfDescricao.setEditable(false);
                    btVendaIdVenda.setEnabled(false);
                    btEditoraIdEditora.setEnabled(false);
                    btGeneroIdGenero.setEnabled(false);
                    btAbrirImagem.setEnabled(false);
                } catch (Exception erro) {
                    jTextArea.append("Erro............");
                    tfIdLivro.setEditable(true);
                    pnNorte.setBackground(Color.red);
                }
            }
        });
        btnUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnUpdate.setVisible(false);
                btnDelete.setVisible(false);
                btAbrirImagem.setEnabled(true);
                tfPreco.requestFocus();
                btnSave.setVisible(true);
                qualAcao = "editar";
                tfPreco.setEditable(true);
                tfDescricao.setEditable(true);
                btVendaIdVenda.setEnabled(true);
                btEditoraIdEditora.setEnabled(true);
                btGeneroIdGenero.setEnabled(true);
            }
        });
        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(null,
                        "Confirma a exclusão do registro <ID = " + livro.getIdLivro() + ">?", "Confirm",
                        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE)) {
                    daoLivro.remover(livro);
                    tfIdLivro.requestFocus();
                    tfPreco.setText("");
                    tfCaminho.setText("");
                    tfDescricao.setText("");
                    tfVendaIdVenda.setText("");
                    tfEditoraIdEditora.setText("");
                    tfGeneroIdGenero.setText("");
                    String caminho = "";

                    icone = new ImageIcon(caminho);
                    rotulo.setIcon(icone);

                    tfCaminho.setText("");
                    tfIdLivro.setEditable(true);
                    btnUpdate.setVisible(false);
                    btnDelete.setVisible(false);
                }
            }
        });
        btnList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GUIListagemLivro guiListagem = new GUIListagemLivro(daoLivro.list());
            }
        });
        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        btAbrirImagem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fc = new JFileChooser();
                fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
                if (fc.showOpenDialog(pnDireita) == JFileChooser.APPROVE_OPTION) {
                    File img = fc.getSelectedFile();
                    String caminho = fc.getSelectedFile().getAbsolutePath();
                    try {
                        tfCaminho.setText(caminho);
                        icone = new javax.swing.ImageIcon(img.getAbsolutePath());
                        imagemAux = icone.getImage();
                        icone.setImage(imagemAux.getScaledInstance(300, 300, Image.SCALE_FAST));
                        rotulo.setIcon(icone);
                    } catch (Exception ex) {
                        System.out.println("Erro: " + ex.getMessage());
                    }
                }
            }
        });
        DAOVenda daoVenda = new DAOVenda();
        btVendaIdVenda.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<String> listaAuxiliar = daoVenda.listInOrderNomeStrings("id");
                if (listaAuxiliar.size() > 0) {
                    String selectedItem = new JanelaPesquisar(listaAuxiliar).getValorRetornado();
                    if (!selectedItem.equals("")) {
                        String[] aux = selectedItem.split("-");
                        tfVendaIdVenda.setText(aux[0]);
                    } else {
                        jTextArea.setText("Nenhum dado adicionado!");
                    }
                }
            }
        });
        DAOEditora daoEditora = new DAOEditora();
        btEditoraIdEditora.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<String> listaAuxiliar = daoEditora.listInOrderNomeStrings("id");
                if (listaAuxiliar.size() > 0) {
                    String selectedItem = new JanelaPesquisar(listaAuxiliar).getValorRetornado();
                    if (!selectedItem.equals("")) {
                        String[] aux = selectedItem.split("-");
                        tfEditoraIdEditora.setText(aux[0]);
                    } else {
                        jTextArea.setText("Nenhum dado adicionado!");
                    }
                }
            }
        });
        DAOGenero daoGenero = new DAOGenero();
        btGeneroIdGenero.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<String> listaAuxiliar = daoGenero.listInOrderNomeStrings("id");
                if (listaAuxiliar.size() > 0) {
                    String selectedItem = new JanelaPesquisar(listaAuxiliar).getValorRetornado();
                    if (!selectedItem.equals("")) {
                        String[] aux = selectedItem.split("-");
                        tfGeneroIdGenero.setText(aux[0]);
                    } else {
                        jTextArea.setText("Nenhum dado adicionado!");
                    }
                }
            }
        });
        tfIdLivro.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<String> listaAuxiliar = daoLivro.listInOrderNomeStrings("id");
                if (listaAuxiliar.size() > 0) {
                    String selectedItem = new JanelaPesquisar(listaAuxiliar).getValorRetornado();
                    if (!selectedItem.equals("")) {
                        String[] aux = selectedItem.split("-");
                        tfIdLivro.setText(aux[0]);
                        btnRetrieve.doClick();

                    } else {
                        tfIdLivro.requestFocus();
                        tfIdLivro.selectAll();
                    }
                }
            }
        });
        CentroDoMonitorMaior centroDoMonitorMaior = new CentroDoMonitorMaior();
        setLocation(centroDoMonitorMaior.getCentroMonitorMaior(this));
        setVisible(true);
    }

    public static void main(String[] args) {
        GUILivro guiLivro = new GUILivro();
    }
}
