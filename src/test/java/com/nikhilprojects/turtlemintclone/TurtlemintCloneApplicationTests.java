package com.nikhilprojects.turtlemintclone;
import com.nikhilprojects.turtlemintclone.exception.ResourceNotFoundException;
import com.nikhilprojects.turtlemintclone.model.Checkout;
import com.nikhilprojects.turtlemintclone.model.Insurer;
import com.nikhilprojects.turtlemintclone.model.Profile;
import com.nikhilprojects.turtlemintclone.model.Quotation;
import com.nikhilprojects.turtlemintclone.repository.CheckoutRepository;
import com.nikhilprojects.turtlemintclone.repository.ProfileRepository;
import com.nikhilprojects.turtlemintclone.repository.QuotationRepository;
import com.nikhilprojects.turtlemintclone.service.CheckoutService;
import com.nikhilprojects.turtlemintclone.service.ProfileService;
import com.nikhilprojects.turtlemintclone.service.QuotationService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.internal.matchers.apachecommons.ReflectionEquals;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.hibernate.validator.internal.util.Contracts.assertTrue;
import static org.junit.Assert.assertEquals;
import org.junit.runner.RunWith;
import org.springframework.data.mongodb.core.aggregation.ArrayOperators;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
@RunWith(SpringRunner.class)
@SpringBootTest
class TurtlemintCloneApplicationTests {
    @Autowired
    private CheckoutService checkoutService;

    @Autowired
    private QuotationService quotationService;

    @Autowired
    private ProfileService profileService;


    @MockBean
    private ProfileRepository profileRepository;

    @MockBean
    private QuotationRepository quotationRepository;

    @MockBean
    private CheckoutRepository checkoutRepository;


    @Test
    public void getAllProfilesTest() {
        Profile profile1 = new Profile("TW", "Bajaj", "pulsar");
        Profile profile2 = new Profile("FW", "Suzuki", "swift");
        Mockito.when(profileRepository.findAll()).thenReturn(Stream.of(profile1, profile2).collect(Collectors.toList()));
        assertEquals(2, profileService.getAllProfiles().size());

    }

    @Test
    public void getProfileByRequestIdTest() throws ResourceNotFoundException {
        Long requestId = 1L;
        Optional<Profile> profile = Optional.of(new Profile("Tw", "Honda", "city"));
        Mockito.when(profileRepository.findById(requestId)).thenReturn(profile);
        assertEquals(profile,profileService.getProfileById(requestId),"Profile not found for this id :: " + requestId);
    }
    @Test
    public void getAllQuotationsTest() {
        Insurer insurerDigit = new Insurer("digit", "1200");
        Insurer insurerChol = new Insurer("chol", "8400");

        Quotation quote1 = new Quotation(1, "TW", "bajaj", "pulsar", new ArrayList<>(Arrays.asList(insurerDigit)));
        Quotation quote2 = new Quotation(2, "FW", "suzuki", "baleno", new ArrayList<>(Arrays.asList(insurerChol)));

        Mockito.when(quotationRepository.findAll()).thenReturn(Stream.of(quote1, quote2).collect(Collectors.toList()));
        assertEquals(2, quotationService.getAllQuotes().size());
    }

    @Test
    public void getQuotationByRequestIdTest() {
        Long requestId = 8L;
        List<Insurer> supportedInsurers= new List<Insurer>() {
            @Override
            public int size() {
                return 0;
            }

            @Override
            public boolean isEmpty() {
                return false;
            }

            @Override
            public boolean contains(Object o) {
                return false;
            }

            @Override
            public Iterator<Insurer> iterator() {
                return null;
            }

            @Override
            public Object[] toArray() {
                return new Object[0];
            }

            @Override
            public <T> T[] toArray(T[] a) {
                return null;
            }

            @Override
            public boolean add(Insurer insurer) {
                return false;
            }

            @Override
            public boolean remove(Object o) {
                return false;
            }

            @Override
            public boolean containsAll(Collection<?> c) {
                return false;
            }

            @Override
            public boolean addAll(Collection<? extends Insurer> c) {
                return false;
            }

            @Override
            public boolean addAll(int index, Collection<? extends Insurer> c) {
                return false;
            }

            @Override
            public boolean removeAll(Collection<?> c) {
                return false;
            }

            @Override
            public boolean retainAll(Collection<?> c) {
                return false;
            }

            @Override
            public void clear() {

            }

            @Override
            public Insurer get(int index) {
                return null;
            }

            @Override
            public Insurer set(int index, Insurer element) {
                return null;
            }

            @Override
            public void add(int index, Insurer element) {

            }

            @Override
            public Insurer remove(int index) {
                return null;
            }

            @Override
            public int indexOf(Object o) {
                return 0;
            }

            @Override
            public int lastIndexOf(Object o) {
                return 0;
            }

            @Override
            public ListIterator<Insurer> listIterator() {
                return null;
            }

            @Override
            public ListIterator<Insurer> listIterator(int index) {
                return null;
            }

            @Override
            public List<Insurer> subList(int fromIndex, int toIndex) {
                return null;
            }
        };
        Insurer digit=new Insurer("digit","2000");
        supportedInsurers.add(1,digit);

        Quotation quote1 = new Quotation(8L, "TW", "bajaj", "pulsar", supportedInsurers);

        Mockito.when(quotationRepository.findAllById(Collections.singleton(requestId))).thenReturn(Stream.of(quote1).collect(Collectors.toList()));
        assertEquals(1, quotationRepository.findByVerticalAndVehicleMakeAndVehicleModel("TW","bajaj","pulsar").getSupportedInsurers().size());
    }
//
    @Test
    public void getAllCheckoutsTest() {
        Insurer insurer1= new Insurer("digit","200");
        Insurer insurer2 = new Insurer("hdfc","1000");
        Checkout checkout1 = new Checkout(100, "Krunal", "something@email.com", "9999999999",insurer1);
        Checkout checkout2 = new Checkout(121, "Suraj", "something1@email.com", "8888888888", insurer2);

        Mockito.when(checkoutRepository.findAll()).thenReturn(Stream.of(checkout1, checkout2).collect(Collectors.toList()));
        assertEquals(2, checkoutService.getAll().size());
    }

    @Test
    public void getCheckoutByCheckoutIdTest() {
        Long checkoutId = 99L;
        Insurer insurer= new Insurer("digit","200");
        Optional<Checkout> checkout = Optional.of(new Checkout(checkoutId, "Krunal", "something@email.com", "9999999999", insurer));

        Mockito.when(checkoutRepository.findById(checkoutId)).thenReturn(checkout);
        assertEquals( checkout,checkoutService.getById(checkoutId),"Profile not found for this id :: " + checkoutId);
    }




    @Test
    public void deleteQuotationTest() {
        Long requestId = 1L;
        quotationService.deleteQuote(requestId);
        verify(quotationRepository, times(1)).deleteById(requestId);
    }

    @Test
    public void deleteCheckout() {
        Long checkoutId = 2L;
        checkoutService.deleteCheckout(checkoutId);
        verify(checkoutRepository, times(1)).deleteById(checkoutId);
    }

}
