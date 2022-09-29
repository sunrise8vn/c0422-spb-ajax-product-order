package com.cg.service.billDetail;


import com.cg.model.BillDetail;
import com.cg.repository.BillDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class BillDetailServiceImpl implements BillDetailService {

    @Autowired
    private BillDetailRepository billDetailRepository;

    @Override
    public List<BillDetail> findAll() {
        return null;
    }

    @Override
    public BillDetail getById(Long id) {
        return null;
    }

    @Override
    public Optional<BillDetail> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public BillDetail save(BillDetail billDetail) {
        return billDetailRepository.save(billDetail);
    }

    @Override
    public void remove(Long id) {

    }
}
