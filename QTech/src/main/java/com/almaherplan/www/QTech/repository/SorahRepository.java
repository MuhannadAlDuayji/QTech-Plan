package com.almaherplan.www.QTech.repository;

import com.almaherplan.www.QTech.model.Sorah;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SorahRepository extends JpaRepository<Sorah,Integer> {

    List<Sorah> findAllByOrderByRid();
    List<Sorah> findAllByOrderByLid();
    Sorah findSorahBySorahAndAndAyah(int sorah, int ayah);

    List<Sorah> findSorahByRidBetweenOrderByRid(int rid1, int rid2);

    List<Sorah> findSorahByLidBetweenOrderByLid(int lid1, int lid2);
    List<Sorah> findSorahByRidGreaterThanEqualOrderByRid(int rid);
    List<Sorah> findSorahByLidGreaterThanEqualOrderByLid(int Lid);

    @Query("SELECT s FROM Sorah s WHERE  s.rid BETWEEN " +
            "(SELECT s.rid FROM Sorah s WHERE s.sorah = ?1 AND s.ayah = ?2) " +
            "AND " +
            "(SELECT s.rid FROM Sorah s WHERE s.sorah = ?3 AND s.ayah = ?4) ORDER BY s.rid")
    List<Sorah> selectSorahFromToOrderByRid(int fromSorah, int fromAyah, int toSorah, int toAyah);

    @Query("SELECT s FROM Sorah s WHERE  s.lid BETWEEN " +
            "(SELECT s1.lid FROM Sorah s1 WHERE s1.sorah = ?1 AND s1.ayah = ?2) " +
            "AND " +
            "(SELECT s2.lid FROM Sorah s2 WHERE s2.sorah = ?3 AND s2.ayah = ?4) ORDER BY s.lid")
    List<Sorah> selectSorahFromToOrderByLid(int fromSorah, int fromAyah, int toSorah, int toAyah);

}
