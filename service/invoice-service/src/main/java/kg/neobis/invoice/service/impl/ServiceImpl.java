package kg.neobis.invoice.service.impl;

import kg.neobis.invoice.dto.NodeDto;
import kg.neobis.invoice.entities.Node;
import kg.neobis.invoice.repository.NodeRepo;
import kg.neobis.invoice.service.Service;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.List;

@org.springframework.stereotype.Service
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ServiceImpl implements Service {

    NodeRepo repo;


    public List<NodeDto> getAll() {
        return repo.findAll().stream()
                .map(this::toNodeDto)
                .toList();
    }


    public Node getById(Long id) {
        return repo.findById(id).orElseThrow(() -> new RuntimeException("node with id %s not fount".formatted(id)));
    }

    private NodeDto toNodeDto(Node node) {
        return NodeDto.builder()
                .id(node.getId())
                .description(node.getDescription())
                .ip(node.getIp())
                .port(node.getPort())
                .build();
    }

}
