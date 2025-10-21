
async function loadPackets() {
    try {
        const response = await fetch('/api/packets/list');
        const packets = await response.json();

        let tbody = '';

        packets.forEach(pkt => {

            // exibir portas (priorizando tcp/udp, senão '-')
            const srcPort = pkt.tcp?.srcPort || pkt.udp?.srcPort || '-';
            const dstPort = pkt.tcp?.dstPort || pkt.udp?.dstPort || '-';

            let tr = `
                <tr>
                    <td>${pkt.timestamp}</td>
                    <td>${pkt.ipv4?.srcIp || '-'}</td>
                    <td>${pkt.ipv4?.dstIp || '-'}</td>
                    <td>${pkt.ipv4?.protocol || '-'}</td>
                    <td>${srcPort}</td>
                    <td>${dstPort}</td>
                </tr>
            `;

            tbody+=tr;
        });

        document.querySelector('#packetTable tbody').innerHTML = tbody;

    } catch (err) {
        console.error('Erro ao carregar pacotes:', err);
    }
}

async function updatePacketCount() {
    try {
        const res = await fetch('/api/packets/counter');
        if (!res.ok) throw new Error('Falha ao requisitar /api/packets/counter');
        const count = await res.json();
        document.getElementById('packetCount').textContent = count;
    } catch (err) {
        console.error('Erro ao atualizar contador de pacotes:', err);
    }
}

async function updateDataLength() {
    try {
        const res = await fetch('/api/packets/sizer');
        if (!res.ok) throw new Error('Falha ao requisitar /api/packets/sizer');
        const bytes = await res.json();

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


setInterval(() => {
    updatePacketCount();
    updateDataLength();
    loadPackets();
}, 1000);

updatePacketCount();
updateDataLength();
loadPackets();
