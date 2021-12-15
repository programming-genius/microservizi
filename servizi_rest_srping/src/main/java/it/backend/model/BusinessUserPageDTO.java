package it.backend.model;

import java.util.List;

public class BusinessUserPageDTO {

    private long total;
    private int start;
    private int max;

    private List<BusinessUserDTO> page;

    public BusinessUserPageDTO(long total, int start, int max, List<BusinessUserDTO> page) {
        this.total = total;
        this.start = start;
        this.max = max;
        this.page = page;
    }

    public List<BusinessUserDTO> getPage() {
        return page;
    }

    public long getTotal() {
        return total;
    }

    public int getStart() {
        return start;
    }

    public int getMax() {
        return max;
    }
}
