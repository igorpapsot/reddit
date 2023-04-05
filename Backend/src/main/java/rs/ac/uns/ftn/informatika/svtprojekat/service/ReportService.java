package rs.ac.uns.ftn.informatika.svtprojekat.service;

import rs.ac.uns.ftn.informatika.svtprojekat.entity.Report;

import java.util.List;

public interface ReportService {

    List<Report> findAllByParent(Report parent);

    Report findOne(Integer id);

    List<Report> findAll();

    Report save(Report report);

    void remove(Integer id);

}
