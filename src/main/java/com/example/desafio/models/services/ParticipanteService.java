package com.example.desafio.models.services;

import com.example.desafio.models.entities.Compromisso;
import com.example.desafio.models.entities.Participante;

import com.example.desafio.models.enums.Situacao;
import com.example.desafio.models.repository.CompromissoRepository;
import com.example.desafio.models.repository.ParticipanteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ParticipanteService {

    @Autowired
    ParticipanteRepository participanteRepository;

    @Autowired
    CompromissoRepository compromissoRepository;

    public Optional<Participante> findById(Long id) {
        try {
            return participanteRepository.findById(id);
        } catch (NullPointerException n) {
            n.getMessage();
        }
        return Optional.empty();
    }

    public List<Participante> findAll() {
        try {
            return participanteRepository.findAll();
        } catch (NullPointerException n) {
            n.getMessage();
            return participanteRepository.findAll();
        }
    }

    public Participante save(Participante participante) {
        return participanteRepository.save(participante);
    }

    public Optional<Participante> update(Long id, Participante participante) {
        Optional<Participante> participanteValue = this.participanteRepository.findById(id).map(p -> {
            if (compromissoRepository.existsById(id)) {
                p.setNome(participante.getNome());
                p.setTelefone(participante.getTelefone());

                return this.participanteRepository.save(p);
            }

            throw new IllegalArgumentException("Não rolou");
        });
        return participanteValue;
    }

    public void delete(Long id) {
        this.participanteRepository.findById(id).map(p -> {
            if (participanteRepository.existsById(id)) {
                this.participanteRepository.deleteById(id);
            } else {
                throw new IllegalArgumentException("Entity cannot be deleted");
            }
            return p;
        });
    }


}

