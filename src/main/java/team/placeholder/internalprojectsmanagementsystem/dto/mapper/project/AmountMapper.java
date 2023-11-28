//package team.placeholder.internalprojectsmanagementsystem.dto.mapper.project;
//
//import team.placeholder.internalprojectsmanagementsystem.dto.model.project.AmountDto;
//import team.placeholder.internalprojectsmanagementsystem.model.project.Amount;
//
//public class AmountMapper {
//    public static AmountDto toAmountDto(Amount amount){
//        if(amount == null){
//            return null;
//        }
//        AmountDto amountDto = new AmountDto();
//        amountDto.setId(amount.getId());
//        amountDto.setBasic_design(amount.getBasic_design());
//        amountDto.setDetail_design(amount.getDetail_design());
//        amountDto.setCoding(amount.getCoding());
//        amountDto.setUnit_testing(amount.getUnit_testing());
//        amountDto.setIntegrated_testing(amount.getIntegrated_testing());
//        return amountDto;
//    }
//
//    public static Amount toAmount(AmountDto amountDto) {
//        if (amountDto==null){
//            return null;
//        }
//        Amount amount= new Amount();
//        amount.setId(amount.getId());
//        amount.setBasic_design(amountDto.getBasic_design());
//        amount.setDetail_design(amountDto.getDetail_design());
//        amount.setCoding(amountDto.getCoding());
//        amount.setUnit_testing(amountDto.getUnit_testing());
//        amount.setIntegrated_testing(amountDto.getIntegrated_testing());
//        return amount;
//    }
//}
