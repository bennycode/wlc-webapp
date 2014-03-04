package de.fhb.rest.v1.dto;

import java.util.Objects;

public class LanguageDTO extends BaseDTO {

  private Integer id;
  private String name;
//	private List<EbookDTO> ebookList;
//	private List<GlossaryDTO> glossaryEntryList;

  public LanguageDTO() {
  }

  public LanguageDTO(Integer id) {
    this.id = id;
  }

  public LanguageDTO(Integer id, String name) {
    this.id = id;
    this.name = name;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  //	@XmlTransient
  //	public List<EbookDTO> getEbookList() {
  //		return ebookList;
  //	}
  //
  //	public void setEbookList(List<EbookDTO> ebookList) {
  //		this.ebookList = ebookList;
  //	}
  //	@XmlTransient
  //	public List<GlossaryEntry> getGlossaryEntryList() {
  //		return glossaryEntryList;
  //	}
  //
  //	public void setGlossaryEntryList(List<GlossaryEntry> glossaryEntryList) {
  //		this.glossaryEntryList = glossaryEntryList;
  //	}
  @Override
  public int hashCode() {
    int hash = 7;
    hash = 67 * hash + Objects.hashCode(this.id);
    hash = 67 * hash + Objects.hashCode(this.name);
    return hash;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    final LanguageDTO other = (LanguageDTO) obj;
    if (!Objects.equals(this.id, other.id)) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "LanguageDTO{" + "id=" + id + '}';
  }
}
