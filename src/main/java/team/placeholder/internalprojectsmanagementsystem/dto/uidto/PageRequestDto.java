package team.placeholder.internalprojectsmanagementsystem.dto.uidto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Objects;

@Getter
@Setter
public class PageRequestDto {
    private Integer pageNo = 0;
    private Integer pageSize = 10;
    private Sort.Direction sort = Sort.Direction.ASC;
    private String sortById = "id";

    public Pageable getPageable(PageRequestDto dto){
        Integer page = Objects.nonNull(dto.getPageSize()) ? dto.pageNo : this.pageNo;
        Integer size = Objects.nonNull(dto.getPageSize()) ? dto.pageSize : this.pageNo;
        Sort.Direction sort = Objects.nonNull(dto.getSort()) ? dto.getSort() : this.sort;
        String sortById = Objects.nonNull(dto.getSortById()) ? dto.getSortById() : this.sortById;
        PageRequest request = PageRequest.of(page,size,sort,sortById);
        return request;
    }
}
