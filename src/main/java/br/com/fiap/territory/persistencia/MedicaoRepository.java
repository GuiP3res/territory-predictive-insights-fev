package br.com.fiap.territory.persistencia;
import br.com.fiap.territory.dominio.Medicao;import org.springframework.data.jpa.repository.JpaRepository;import java.util.List;
public interface MedicaoRepository extends JpaRepository<Medicao,Long>{List<Medicao> findBySensorIdOrderByInstanteAsc(String sensorId);}