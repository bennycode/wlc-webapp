package com.welovecoding.workshop.data;

import com.welovecoding.workshop.model.Aktion;
import com.welovecoding.workshop.model.Konto;
import com.welovecoding.workshop.model.Spende;
import com.welovecoding.workshop.model.Spende.Status;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@SessionScoped
@Named
public class AktionListProducer implements Serializable {

  public AktionListProducer() {
    this.aktionen = createMockAktionen();
  }

  private List<Aktion> aktionen;

  public List<Aktion> getAktionen() {
    return aktionen;
  }

  private List<Aktion> createMockAktionen() {
    Spende spende1 = new Spende();
    spende1.setSpenderName("Daniel Schmidt");
    spende1.setBetrag(20L);
    spende1.setQuittung(true);
    spende1.setStatus(Status.UEBERWIESEN);

    Konto konto1 = new Konto(spende1.getSpenderName(), "XXX Bank", "123456", "88465465");
    spende1.setKonto(konto1);

    List<Spende> spenden = new LinkedList<>();

    Aktion aktion2 = new Aktion();
    aktion2.setName("Rollstuhl für Maria");
    aktion2.setSpendenZiel(2500d);
    aktion2.setBisherGespendet(742d);
    aktion2.setSpendenBetrag(25d);
    aktion2.setId(2L);
    aktion2.setKonto(konto1);
    aktion2.setSpenden(spenden);

    List<Aktion> returnValue = new LinkedList<>();
    returnValue.add(aktion2);

    return returnValue;
  }

}
