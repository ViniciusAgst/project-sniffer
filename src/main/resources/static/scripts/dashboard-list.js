function formatTimestamp(isoString) {
    if (!isoString) return '-';
    try {
        const date = new Date(isoString);
        return date.toLocaleString('pt-BR', {
            day: '2-digit',
            month: '2-digit',
            year: 'numeric',
            hour: '2-digit',
            minute: '2-digit',
            second: '2-digit',
            hour12: false
        });
    } catch (e) {
        return isoString;
    }
}

/**
 * Mapa de portas conhecidas -> nome do serviço.
 * Adicione ou remova entradas conforme desejar.
 */
const wellKnownPorts = {
    20: 'FTP-data', 21: 'FTP', 22: 'SSH', 23: 'Telnet',
    25: 'SMTP', 53: 'DNS', 67: 'DHCP', 68: 'DHCP',
    80: 'HTTP', 110: 'POP3', 123: 'NTP', 143: 'IMAP',
    161: 'SNMP', 194: 'IRC', 443: 'HTTPS', 465: 'SMTPS',
    587: 'SMTP (submissão)', 993: 'IMAPS', 995: 'POP3S',
    1433: 'MSSQL', 1521: 'Oracle', 3306: 'MySQL',
    3389: 'RDP', 5432: 'PostgreSQL'
};

function portToService(port) {
    if (!port) return '-';
    const p = Number(port);
    return wellKnownPorts[p] || p;
}

/**
 * Monta uma string descritiva do "protocolo/serviço" do pacote.
 * Prioriza identificação por camada de transporte (TCP/UDP) se disponível.
 */
function detectProtocolLabel(pkt) {
    // checar TCP
    if (pkt.tcp) {
        const src = portToService(pkt.tcp.srcPort);
        const dst = portToService(pkt.tcp.dstPort);
        if (src === dst) return `TCP / ${src}`;
        return `TCP / ${src} → ${dst}`;
    }

    // checar UDP (se existir)
    if (pkt.udp) {
        const src = portToService(pkt.udp.srcPort);
        const dst = portToService(pkt.udp.dstPort);
        if (src === dst) return `UDP / ${src}`;
        return `UDP / ${src} → ${dst}`;
    }

    // fallback para identificar pelo campo ipv4.protocol (se existir)
    if (pkt.ipv4 && pkt.ipv4.protocol) {
        // muitos capturadores usam números de protocolo (6=tcp, 17=udp)
        const protoNum = String(pkt.ipv4.protocol);
        if (protoNum === '6') return 'TCP';
        if (protoNum === '17') return 'UDP';
        return `Proto ${protoNum}`;
    }

    return '-';
}

async function loadPackets() {
    try {
        const response = await fetch('/frames');
        const packets = await response.json();

        const tbody = document.querySelector('#packetTable tbody');
        tbody.innerHTML = '';

        packets.forEach(pkt => {
            const protocolLabel = detectProtocolLabel(pkt);

            // exibir portas (priorizando tcp/udp, senão '-')
            const srcPort = pkt.tcp?.srcPort || pkt.udp?.srcPort || '-';
            const dstPort = pkt.tcp?.dstPort || pkt.udp?.dstPort || '-';

            const tr = document.createElement('tr');
            tr.innerHTML = `
                <td>${formatTimestamp(pkt.timestamp)}</td>
                <td>${pkt.ipv4?.srcIp || '-'}</td>
                <td>${pkt.ipv4?.dstIp || '-'}</td>
                <td>${protocolLabel}</td>
                <td>${srcPort}</td>
                <td>${dstPort}</td>
            `;

            frames.appendChild(tr);
        });

    } catch (err) {
        console.error('Erro ao carregar pacotes:', err);
    }
}

setInterval(loadPackets, 1000);
loadPackets();
