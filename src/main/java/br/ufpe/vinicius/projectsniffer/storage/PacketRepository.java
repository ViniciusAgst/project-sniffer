package br.ufpe.vinicius.projectsniffer.storage;

import org.springframework.data.repository.CrudRepository;

public interface PacketRepository extends CrudRepository<PacketDTO, Long> {
}
