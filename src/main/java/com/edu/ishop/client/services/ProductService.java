package com.edu.ishop.client.services;


import com.edu.ishop.helpers.exceptions.ResponseException;
import com.edu.ishop.helpers.repository.CategoryRepository;
import com.edu.ishop.helpers.repository.FeedBackRepository;
import com.edu.ishop.helpers.repository.ProductManufactureRepository;
import com.edu.ishop.helpers.repository.ProductRepository;
import com.edu.ishop.helpers.entity.*;
import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static com.edu.ishop.helpers.specification.ProductSpecifications.*;

@Service
public class ProductService {
    ProductRepository productRepository;
    ProductManufactureRepository productManufactureRepository;
    CategoryService categoryService;
    CategoryRepository categoryRepository;

    ProductTrader productTrader;
    FeedBackRepository feedBackRepository;
    FeedBack feedBack;

    @Autowired
    public ProductService(CategoryRepository categoryRepository, ProductManufactureRepository productManufactureRepository, FeedBackRepository feedBackRepository, ProductRepository productRepository, CategoryService categoryService, List<Product> productList, FeedBack feedBack) {
        this.productRepository = productRepository;
        this.productManufactureRepository = productManufactureRepository;
        this.feedBackRepository = feedBackRepository;
        this.categoryService = categoryService;
        this.categoryRepository = categoryRepository;
        this.productTrader = productTrader;
        this.feedBack = feedBack;
    }


    //            this.nameProduct = nameProduct;
//        this.urlImage = urlImage;
//        this.price = price;
//        this.rating = rating;
//        this.quantityStock = quantityStock;
//        this.dateAdded = dateAdded;
//        this.productTrader = productTrader;
//        this.feedBack = feedBack;
//        this.categoryProduct = categoryProduct;
    public List<Product> getProduct() {

        LocalDate date2010 = LocalDate.of(2010, 12, 31);
        LocalDate date2011 = LocalDate.of(2011, 12, 31);
        LocalDate date2012 = LocalDate.of(2012, 12, 31);
        LocalDate date2013 = LocalDate.of(2013, 12, 31);
        LocalDate date2014 = LocalDate.of(2014, 12, 31);
        LocalDate date2015 = LocalDate.of(2015, 12, 31);
        ZonedDateTime zonedDT11 = ZonedDateTime.parse("2010-10-10T18:30:45+01:00[Europe/London]");
        ZonedDateTime zonedDT12 = ZonedDateTime.parse("2011-04-22T08:40:15+10:00[Australia/Sydney]", DateTimeFormatter.ISO_DATE_TIME);

        ProductTrader productTrader1 = new ProductTrader("MilkCorp", "Russia",
                date2015, 4.9, new BigDecimal("4.789"), 7, "MSK", true);
        productManufactureRepository.save(productTrader1);
        Customer customer = new Customer();
        FeedBack feedBack1 = new FeedBack("Отличный, вкусный продукт", null, customer);

        Product milk = new Product("milk", "http://milk.com", new BigDecimal("234.56"), 4.5, 99_999, date2010, productTrader1, categoryService.dairyProducts);

        Product hamburger = new Product("hamburger", "http://hamburger.com", new BigDecimal("345.56"), 4.0, 95, date2011, productTrader1, categoryService.readyMadeFood);


        Product washingMachine = new Product("washingMachine", "http://washingMachine.com", new BigDecimal("11234.56"), 4.2, 99_999, date2012, productTrader1, categoryService.homeAppliances);
        Product Iphone19 = new Product("Iphone19", "http://Iphone19.com", new BigDecimal("5561.56"), 3.5, 12399_999, date2013, productTrader1, categoryService.electronic);
        Product orange = new Product("orange", "http://orange.com", new BigDecimal("34.60"), 4.1, 2_999, date2014, productTrader1, categoryService.healthyFood);


        Product carrot = new Product("carrot", "http://carrot.com", new BigDecimal("24.57"), 4.9, 67_999, date2015, productTrader1, categoryService.healthyFood);
        Product sportsShoes = new Product("sportsShoes", "http://sportsShoes.com", new BigDecimal("4234.56"), 4.2, 78_999, date2010, productTrader1, categoryService.sport);
        Product sportsTrousers = new Product("sportsTrousers", "http://sportsTrousers.com", new BigDecimal("7345.77"), 4.3, 89_999, date2010, productTrader1, categoryService.sport);


        List<Product> productList = new ArrayList<>();
        productList.add(milk);
        productList.add(hamburger);
        productList.add(washingMachine);
        productList.add(Iphone19);
        productList.add(orange);
        productList.add(carrot);
        productList.add(sportsShoes);
        productList.add(sportsTrousers);
        return productList;
    }


    public List<Product> getProductList(String url) {
        return productRepository.findByCategoryProductUrl(url);
    }

    public List<Product> getProductsByRandomCategoryAndRating() {
        List<Product> productList;
        List<Category> categoriesAll = categoryRepository.findAll();

        Pageable limit = PageRequest.of(0, 6);
        do {
            int randomIndexCategories = (int) (Math.random() * categoriesAll.size());
            Category randomCategory = categoriesAll.get(randomIndexCategories);
            productList = productRepository.findAll(findProductbyCategoryAndRatingAndDate(
                    randomCategory.getId(), 4.0, LocalDate.now().minusDays(30)
            ).and(findProductActiveManufactureAndCountUpZero()), limit).getContent();
        } while (productList.size() == 0);

        return productList;
    }

    public Product getProductById(int id) {
        Product product = productRepository.findById(id).orElse(null);
//       List<FeedBack> feedBacks = product.getFeedBack();

        return product;
    }

    ;


    public List<Product> getProductListByName(String[] nameArr) {
        return productRepository.getProductsByName(nameArr);
    }

    public FeedBack addFeedBackToProduct(int id, String textFeedBack) {
        Customer customer = new Customer();
        Product product = productRepository.findById(id).orElse(null);
        if (product == null) return null;
        FeedBack feedBack = new FeedBack(textFeedBack, product, customer);
        product.setFeedbackToList(feedBack);
        feedBackRepository.save(feedBack);
        return feedBack;
    }

    ;
//    List<Product>findProductByProductManufacturerAndQuantityStockAndRating(ProductManufactureId id,int quontity,short rating);

    public List<Product> getProductByProductManufacturerAndQuantityStockAndRating(String country, String name, int quantity, double rating) throws ResponseException {

        if (quantity < 0 || rating < 0 || rating > 5)
            throw new ResponseException("Рейтинг должен быть от 0 до 5 и количество больше 0");
        ProductManufactureId productManufactureIdOUT = new ProductManufactureId(name, country);
//        return productRepository.findProductByProductManufacturerAndQuantityStockAndRating(productManufactureIdOUT, quantity,rating);
        return productRepository.findAll
                (findProductByProductManufacturerAndQuantityStockAndRating(productManufactureIdOUT,
                        quantity, rating).and(findProductActiveManufactureAndCountUpZero()));
    }

    @Bean
    public CommandLineRunner createTableProduct() {
        return (args) -> {
            productRepository.saveAll(getProduct());

        };
    }

    public List<Product> getProductByName(String name) {
        return productRepository.findAll(findProductByName(name));
    }

    ;

    public List<Product> getProductByCategoryAndQuantityAndRatingAndManufacturerName(
            String categoryURL, int quantity, double rating, List<String> namesManufacturer, int page, int size,
            String orderSort
    ) {
        Sort sort = Sort.by("price");
        if (orderSort == null || orderSort.equals("asc")) {
            sort = Sort.by("price");
        }
        if (orderSort != null && orderSort.equals("desc")) {
            sort = Sort.by("price");
        }

        Pageable pageable = PageRequest.of(page, size, sort);


        return productRepository.findAll(findByCategoryAndQuantityAndRatingAndManufacturerName
                (categoryURL, quantity, rating, namesManufacturer), pageable).getContent();

    }


    public boolean uploadFile(MultipartFile file, String userNameTrader) {
        InputStream in = null;
        try {
            in = file.getInputStream();
        } catch (IOException e) {
            return false;
        }

        File currDir = new File("files/" + userNameTrader);
        if (!currDir.exists()) currDir.mkdirs();
        System.out.println("PATH DIR = " + currDir.getAbsolutePath());

        String path = currDir.getAbsolutePath();
        String fileLocation = path + "/" + file.getOriginalFilename();
//        "/files/userNameTrader/products.excel"

        try (FileOutputStream fileOutputStream = new FileOutputStream(fileLocation)) {
            int readCount = 0;
            while ((readCount = in.read()) != -1) {
                fileOutputStream.write(readCount);
            }
            fileOutputStream.flush();
        } catch (IOException exception) {
            return false;
        }


        return true;
    }


    public List<Product> readExcelToList(MultipartFile file, ProductTrader trader) {
 List<Product> productList = new ArrayList<>();
        try {
            Workbook workbook = WorkbookFactory
                    .create(new File("files/" + trader.getUserName() + "/" + file.getOriginalFilename()));

            Sheet sheet = workbook.getSheetAt(0);
            int rowNumber = 0;
            for (Row row : sheet) {
                if (rowNumber == 0) {
                    rowNumber++;
                    continue;
                }
                Product product = new Product();
                product.setDateAdded(LocalDate.now());
                product.setProductManufacturer(trader);
                for (Cell cell : row) {

                    int cellIndex = cell.getColumnIndex();

                    switch (cellIndex) {
                        case 0 -> {
                            if(cell.getStringCellValue() == null | cell.getStringCellValue().isEmpty() ) break;
                            product.setNameProduct(cell.getStringCellValue());
                        }
                        case 1 -> product.setQuantityStock((int) cell.getNumericCellValue());
                        case 2 -> product.setPrice(BigDecimal.valueOf(cell.getNumericCellValue()));
                        case 3 -> product.setUrlImage(cell.getStringCellValue());
                        case 4 -> {
                            Category category = categoryRepository.findByUrl(cell.getStringCellValue());
                            System.out.println("category.getUrl() === " + category.getUrl());
                            if (category == null) break;
                            product.setCategoryProduct(category);
                        }

                    }

                }
                productList.add(product);
            }
        } catch (IOException e) {
           return  null;
        }
        return productList;
    }


    public List<String> uploadProducts(MultipartFile file, String userNameTrader) throws ResponseException {
       ProductTrader trader =  productManufactureRepository.findById(userNameTrader).orElse(null);
      if(trader == null) throw new ResponseException("Продавец не зарегистрирован");

        boolean isUpload = uploadFile(file, userNameTrader);
        if (!isUpload) return null;
        List<Product> productList = readExcelToList(file,trader);
        if(productList == null) return null;
        productRepository.saveAll(productList);
        List<String> nameProductList = productList.stream().map((product)-> product.getNameProduct()).toList();
        return nameProductList;
    }
}

//import org.springframework.data.domain.PageRequest;
//        import org.springframework.data.domain.Pageable;
//1. GET:: /product/category/{categoryUrl} - постраничное получение всех товаров определенной категории
// с возможностью сортировки по возрастанию / убыванию цены или новизне (отзывы не нужны).
//        Номер страницы, количество записей и порядок сортировки передаются параметрами и
//        являются обязательными.Добавить фильтрацию по доступному количеству (больше или равно
//        указанному), по рейтингу (больше или равно указанному),
//        по производителю (один или несколько). Количество, рейтинг, производитель передаются
//        параметрами и не являются обязательными.
