package br.edu.ifpb.sr.dac.demo.service.chamado;

import br.edu.ifpb.sr.dac.demo.dao.ChamadoDao;
import br.edu.ifpb.sr.dac.demo.dao.UsuarioDao;
import br.edu.ifpb.sr.dac.demo.dto.ChamadoMapper;
import br.edu.ifpb.sr.dac.demo.dto.GetChamadosRespDTO;
import br.edu.ifpb.sr.dac.demo.dto.PostChamadoReqDTO;
import br.edu.ifpb.sr.dac.demo.model.Chamado;
import br.edu.ifpb.sr.dac.demo.model.StatusChamado;
import br.edu.ifpb.sr.dac.demo.model.Usuario;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChamadoServiceImpl implements ChamadoService {

    private final ChamadoDao chamadoDao;

    private final ChamadoMapper chamadoMapper;

    private final UsuarioDao usuarioDao;

    public ChamadoServiceImpl(ChamadoDao chamadoDao, ChamadoMapper chamadoMapper, UsuarioDao usuarioDao) {
        this.chamadoDao = chamadoDao;
        this.chamadoMapper = chamadoMapper;
        this.usuarioDao = usuarioDao;
    }

    @Override
    @Transactional
    public void salvar(PostChamadoReqDTO dto) {
        Usuario dono = this.usuarioDao.findById(dto.idDono()).orElseThrow(() -> new RuntimeException("Dono do chamado não encontrado"));
        Chamado chamado = chamadoMapper.toEntity(dto);
        chamado.setDono(dono);
        chamado.setStatus(StatusChamado.ABERTO);
        this.chamadoDao.save(chamado);
    }

    @Override
    public List<GetChamadosRespDTO> buscarTodosPorUsuario(Long idUsuario) {
        return this.chamadoDao.findAllByDono_Id(idUsuario).stream().map(this.chamadoMapper::toGetChamadosRespDTO).toList();
    }
}
