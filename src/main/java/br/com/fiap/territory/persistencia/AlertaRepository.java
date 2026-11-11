package br.com.fiap.territory.persistencia;
import br.com.fiap.territory.dominio.Alerta;import org.springframework.data.jpa.repository.JpaRepository;import java.util.List;
public interface AlertaRepository extends JpaRepository<Alerta,Long>{List<Alerta> findAllByOrderByInstanteDesc();}