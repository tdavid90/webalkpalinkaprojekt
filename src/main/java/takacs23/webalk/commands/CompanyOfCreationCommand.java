package takacs23.webalk.commands;

public class CompanyOfCreationCommand {
    private Long id;
    private String companyName;

    public CompanyOfCreationCommand() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}
