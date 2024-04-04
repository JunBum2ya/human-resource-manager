package com.ecorich.hrservice.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class PetAttractResult {

    private Response response;

    @Getter
    @Setter
    public static class Response {
        private Header header;
        private Body body;

        @Getter
        @Setter
        public static class Header {
            private String resultCode;
            private String resultMsg;
        }

        @Getter
        @Setter
        public static class Body {
            private Items items;
            private Integer pageSize;
            private Integer pageNumber;
            private Long totalCount;

            @Getter
            @Setter
            public static class Items {
                private List<PetAttractItem> item;
            }
        }
    }

    public List<PetAttractItem> getItems() {
        if(this.response.getBody().getItems() != null) {
            return this.response.getBody().getItems().getItem();
        }else {
            return new ArrayList<>();
        }
    }
    public Long getTotalCount() {
        return this.response.getBody().getTotalCount();
    }

}
