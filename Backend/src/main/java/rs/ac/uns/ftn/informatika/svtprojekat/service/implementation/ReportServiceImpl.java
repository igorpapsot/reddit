package rs.ac.uns.ftn.informatika.svtprojekat.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.informatika.svtprojekat.entity.Report;
import rs.ac.uns.ftn.informatika.svtprojekat.repository.ReportRepository;
import rs.ac.uns.ftn.informatika.svtprojekat.service.ReportService;

import java.util.List;

@Service
public class ReportServiceImpl implements ReportService {

    @Autowired
    ReportRepository repository;

    @Override
    public List<Report> findAllByParent(Report parent) {
        return null;
    }

    @Override
    public Report findOne(Integer id) {
        return null;
    }

    @Override
    public List<Report> findAll() {
        return null;
    }

    @Override
    public Report save(Report report) {
        return null;
    }

    @Override
    public void remove(Integer id) {

    }
}
