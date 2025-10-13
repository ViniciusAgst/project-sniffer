async function updatePacketCount() {
    try {
        const res = await fetch('/frames-size');
        if (!res.ok) throw new Error('Falha ao requisitar /frames-size');
        const count = await res.json();
        document.getElementById('packetCount').textContent = count;
    } catch (err) {
        console.error('Erro ao atualizar contador de pacotes:', err);
    }
}

async function updateDataLength() {
    try {
        const res = await fetch('/frames-length');
        if (!res.ok) throw new Error('Falha ao requisitar /frames-length');
        const bytes = await res.json();

        // Converter tamanho em formato legível
        let sizeText;
        if (bytes < 1024) sizeText = `${bytes} B`;
        else if (bytes < 1048576) sizeText = `${(bytes / 1024).toFixed(2)} KB`;
        else if (bytes < 1073741824) sizeText = `${(bytes / 1048576).toFixed(2)} MB`;
        else sizeText = `${(bytes / 1073741824).toFixed(2)} GB`;

        document.getElementById('dataLength').textContent = sizeText;
    } catch (err) {
        console.error('Erro ao atualizar contador de dados:', err);
    }
}

// Atualiza ambos a cada 2 segundos
setInterval(() => {
    updatePacketCount();
    updateDataLength();
}, 1000);

// Atualiza logo ao carregar
updatePacketCount();
updateDataLength();