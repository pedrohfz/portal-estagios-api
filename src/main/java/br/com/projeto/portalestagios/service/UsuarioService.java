package br.com.projeto.portalestagios.service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.projeto.portalestagios.dto.LoginRequestDTO;
import br.com.projeto.portalestagios.dto.UsuarioRequestDTO;
import br.com.projeto.portalestagios.dto.UsuarioResponseDTO;
import br.com.projeto.portalestagios.entity.Perfil;
import br.com.projeto.portalestagios.entity.Usuario;
import br.com.projeto.portalestagios.exception.EntityNotFoundException;
import br.com.projeto.portalestagios.exception.ObjetoCadastradoException;
import br.com.projeto.portalestagios.mapper.UsuarioMapper;
import br.com.projeto.portalestagios.repository.UsuarioRepository;

@Service
public class UsuarioService implements UserDetailsService {

    @Autowired
    private UsuarioMapper usuarioMapper;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<UsuarioResponseDTO> getAllUsuarios() {
        List<Usuario> usuarios = usuarioRepository.findAll();
        return usuarios.stream().map(usuarioMapper::toDto).collect(Collectors.toList());
    }

    public UsuarioResponseDTO getUsuarioById(Long id) {
        Usuario usuario = usuarioRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Usuario", id));
        return usuarioMapper.toDto(usuario);
    }

    public UsuarioResponseDTO salvar(UsuarioRequestDTO usuarioDTO) {

        if (usuarioRepository.findByEmail(usuarioDTO.getEmail()).isPresent()) {
            throw new ObjetoCadastradoException("O e-mail '" + usuarioDTO.getEmail() + "' já está cadastrado.");
        }

        usuarioDTO.setSenha(passwordEncoder.encode(usuarioDTO.getSenha()));

        Usuario usuario = usuarioMapper.toEntity(usuarioDTO);
        usuario.setPerfil(Perfil.ADMIN);

        return usuarioMapper.toDto(usuarioRepository.save(usuario));
    }

    public UsuarioResponseDTO atualizar(Long id, UsuarioRequestDTO usuarioDTO) {
        Usuario usuarioAtualizar = usuarioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuario", id));

        usuarioAtualizar.setNome(usuarioDTO.getNome());
        usuarioAtualizar.setEmail(usuarioDTO.getEmail());
        Usuario usuario = usuarioRepository.save(usuarioAtualizar);

        return usuarioMapper.toDto(usuario);
    }

    public void deletar(Long id) {
        Usuario usuario = usuarioRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Usuario", id));
        usuarioRepository.delete(usuario);
    }

    public UsuarioResponseDTO autenticar(LoginRequestDTO dto) {
        Usuario usuario = usuarioRepository.findByEmail(dto.getEmail()).get();
        return usuarioMapper.toDto(usuario);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado com email: " + email));

        System.out.println("Usuario: " + usuario.getEmail() + " Perfil: " + usuario.getPerfil());

        List<SimpleGrantedAuthority> authorities = Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + usuario.getPerfil().name()));

        return new User(usuario.getEmail(), usuario.getSenha(), authorities);
    }

}
