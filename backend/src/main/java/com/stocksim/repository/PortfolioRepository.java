package com.stocksim.repository;
import com.stocksim.model.PortfolioItem;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface PortfolioRepository extends JpaRepository<PortfolioItem,Long> {
    List<PortfolioItem> findByUserId(Long userId);
    PortfolioItem findByUserIdAndSymbol(Long userId, String symbol);
}
