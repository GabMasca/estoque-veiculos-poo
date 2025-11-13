const API_URL = "http://localhost:8080";

// Ao iniciar a página
window.onload = () => {
    carregarMarcas();
    carregarVeiculos();
};

// ======== MARCAS ========

// Cadastrar marca
async function cadastrarMarca() {
    const nome = document.getElementById("marcaNome").value;
    if (!nome) {
        alert("Digite o nome da marca!");
        return;
    }

    await fetch(`${API_URL}/marcas`, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ nome })
    });

    mostrarMensagem("Marca cadastrada com sucesso!");
    document.getElementById("marcaNome").value = "";
    carregarMarcas();
}

// Carregar marcas
async function carregarMarcas() {
    const res = await fetch(`${API_URL}/marcas`);
    const marcas = await res.json();

    const selects = [
        document.getElementById("marcaSelect"),
        document.getElementById("editarMarcaSelect"),
        document.getElementById("filtroMarca")
    ];

    selects.forEach(select => {
        select.innerHTML = select.id === "filtroMarca"
            ? '<option value="">Todas</option>'
            : "";

        marcas.forEach(marca => {
            const option = document.createElement("option");
            option.value = marca.id;
            option.textContent = marca.nome;
            select.appendChild(option);
        });
    });
}

// ======== VEÍCULOS ========

// Cadastrar veículo
async function cadastrarVeiculo() {
    const modelo = document.getElementById("modelo").value;
    const ano = document.getElementById("ano").value;
    const cor = document.getElementById("cor").value;
    const quilometragem = document.getElementById("quilometragem").value;
    const preco = document.getElementById("preco").value;
    const status = document.getElementById("status").value;
    const marcaId = document.getElementById("marcaSelect").value;

    if (!modelo || !ano || !cor || !quilometragem || !preco || !marcaId) {
        alert("Preencha todos os campos!");
        return;
    }

    await fetch(`${API_URL}/veiculos`, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({
            modelo,
            ano: parseInt(ano),
            cor,
            quilometragem: parseFloat(quilometragem),
            preco: parseFloat(preco),
            status,
            marca: { id: parseInt(marcaId) }
        })
    });

    mostrarMensagem("Veículo cadastrado com sucesso!");
    document.getElementById("modelo").value = "";
    document.getElementById("ano").value = "";
    document.getElementById("cor").value = "";
    document.getElementById("quilometragem").value = "";
    document.getElementById("preco").value = "";
    carregarVeiculos();
}

// Listar veículos
async function carregarVeiculos() {
    const res = await fetch(`${API_URL}/veiculos`);
    const veiculos = await res.json();
    mostrarVeiculos(veiculos);
}

// Filtrar por marca
async function filtrarVeiculos() {
    const marcaId = document.getElementById("filtroMarca").value;
    let url = `${API_URL}/veiculos`;
    if (marcaId) url = `${API_URL}/veiculos/marca/${marcaId}`;

    const res = await fetch(url);
    const veiculos = await res.json();
    mostrarVeiculos(veiculos);
}

// Mostrar veículos na tabela
function mostrarVeiculos(veiculos) {
    const tbody = document.querySelector("#tabelaVeiculos tbody");
    tbody.innerHTML = "";

    veiculos.forEach(v => {
        const tr = document.createElement("tr");
        tr.innerHTML = `
            <td>${v.id}</td>
            <td>${v.modelo}</td>
            <td>${v.marca?.nome || "—"}</td>
            <td>${v.ano}</td>
            <td>${v.cor}</td>
            <td>R$ ${v.preco.toFixed(2)}</td>
            <td>${v.quilometragem} km</td>
            <td>${v.status}</td>
            <td>
                <button onclick="editarVeiculo(${v.id})">✏️ Editar</button>
                <button onclick="deletarVeiculo(${v.id})">🗑️ Excluir</button>
            </td>
        `;
        tbody.appendChild(tr);
    });
}

// Deletar veículo
async function deletarVeiculo(id) {
    if (confirm("Tem certeza que deseja excluir este veículo?")) {
        await fetch(`${API_URL}/veiculos/${id}`, { method: "DELETE" });
        mostrarMensagem("Veículo removido com sucesso!");
        carregarVeiculos();
    }
}

// ======== EDIÇÃO ========

// Preencher formulário
async function editarVeiculo(id) {
    const res = await fetch(`${API_URL}/veiculos`);
    const veiculos = await res.json();
    const v = veiculos.find(item => item.id === id);

    document.getElementById("editarId").value = v.id;
    document.getElementById("editarModelo").value = v.modelo;
    document.getElementById("editarAno").value = v.ano;
    document.getElementById("editarCor").value = v.cor;
    document.getElementById("editarQuilometragem").value = v.quilometragem;
    document.getElementById("editarPreco").value = v.preco;
    document.getElementById("editarStatus").value = v.status;
    document.getElementById("editarMarcaSelect").value = v.marca?.id || "";

    document.getElementById("editarVeiculoSection").style.display = "block";
    window.scrollTo(0, document.body.scrollHeight);
}

// Salvar edição
async function salvarEdicao() {
    const id = document.getElementById("editarId").value;
    const modelo = document.getElementById("editarModelo").value;
    const ano = document.getElementById("editarAno").value;
    const cor = document.getElementById("editarCor").value;
    const quilometragem = document.getElementById("editarQuilometragem").value;
    const preco = document.getElementById("editarPreco").value;
    const status = document.getElementById("editarStatus").value;
    const marcaId = document.getElementById("editarMarcaSelect").value;

    await fetch(`${API_URL}/veiculos/${id}`, {
        method: "PUT",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({
            modelo,
            ano: parseInt(ano),
            cor,
            quilometragem: parseFloat(quilometragem),
            preco: parseFloat(preco),
            status,
            marca: { id: parseInt(marcaId) }
        })
    });

    mostrarMensagem("Veículo atualizado com sucesso!");
    document.getElementById("editarVeiculoSection").style.display = "none";
    carregarVeiculos();
}

// Cancelar edição
function cancelarEdicao() {
    document.getElementById("editarVeiculoSection").style.display = "none";
}

// Mensagem de feedback
function mostrarMensagem(texto, cor = "#67D02F") {
    const msg = document.getElementById("mensagem");
    msg.textContent = texto;
    msg.style.backgroundColor = cor;
    msg.style.display = "block";
    setTimeout(() => msg.style.display = "none", 3000);
}
