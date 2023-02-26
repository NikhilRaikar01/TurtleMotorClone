package com.nikhilprojects.turtlemintclone;
import com.nikhilprojects.turtlemintclone.repository.CheckoutRepository;
import com.nikhilprojects.turtlemintclone.repository.ProfileRepository;
import com.nikhilprojects.turtlemintclone.repository.QuotationRepository;
import com.nikhilprojects.turtlemintclone.service.CheckoutService;
import com.nikhilprojects.turtlemintclone.service.QuotationService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
class TurtlemintCloneApplicationTests {

    @Test
    void itShouldGenerateId() {

    }
    @Autowired
    private CheckoutService checkoutService;

    @Autowired
    private QuotationService quotationService;


    @MockBean
    private ProfileRepository profileRepository;

    @MockBean
    private QuotationRepository quotationRepository;

    @MockBean
    private CheckoutRepository checkoutRepository;


    @Test
    public void getAllProfilesTest() {
        Profile profile1 = new Profile("123lakjsdf", "TW", "bajaj", "pulsar", "123123123");
        Profile profile2 = new Profile("43jhkh2hjj", "FW", "suzuki", "baleno", "432423423");

        when(profileRepository.findAll()).thenReturn(Stream.of(profile1, profile2).collect(Collectors.toList()));
        assertEquals(2, profileService.getAllProfiles().size());
    }

    @Test
    public void getProfileByRequestIdTest() {
        String requestId = "845477903302113819";
        Profile profile = new Profile("123lj123lj", "TW", "suzuki", "gixer", "845477903302113819");

        when(profileRepository.findByRequestId(requestId)).thenReturn(profile);
        assertEquals(profile, profileService.getProfileByRequestId(requestId));
    }

    @Test
    public void getAllQuotationsTest() {
        Insurer insurerDigit = new Insurer("digit", "1200");
        Insurer insurerChol = new Insurer("chol", "8400");

        Quotation quote1 = new Quotation("123lakjsdf", "TW", "bajaj", "pulsar", new ArrayList<>(Arrays.asList(insurerDigit)));
        Quotation quote2 = new Quotation("43jhkh2hjj", "FW", "suzuki", "baleno", new ArrayList<>(Arrays.asList(insurerChol)));

        when(quotationRepository.findAll()).thenReturn(Stream.of(quote1, quote2).collect(Collectors.toList()));
        assertEquals(2, quotationServiceImplementation.getAllQuotations().size());
    }

    @Test
    public void getQuotationByRequestIdTest() {
        String requestId = "845477903302113819";
        Insurer insurerDigit = new Insurer("digit", "1200");
        Quotation quote1 = new Quotation("123lakjsdf", "TW", "bajaj", "pulsar", new ArrayList<>(Arrays.asList(insurerDigit)));

        when(quotationRepository.findAllById(Collections.singleton(requestId))).thenReturn(Stream.of(quote1).collect(Collectors.toList()));
        assertEquals(1, quotationServiceImplementation.getQuotationByRequestId(requestId).size());
    }

    @Test
    public void getAllCheckoutsTest() {
        Checkout checkout1 = new Checkout("845477903302113819", "123111115512", "Krunal", "something@email.com", "9999999999", "digit");
        Checkout checkout2 = new Checkout("132123123123455155", "123445155512", "Suraj", "something1@email.com", "8888888888", "chol");

        when(checkoutRepository.findAll()).thenReturn(Stream.of(checkout1, checkout2).collect(Collectors.toList()));
        assertEquals(2, checkoutServiceImplementation.getAllCheckouts().size());
    }

    @Test
    public void getCheckoutByCheckoutIdTest() {
        String checkoutId = "845477903302113819";
        Checkout checkout = new Checkout(checkoutId, "123445155512", "Krunal", "something@email.com", "9999999999", "digit");

        when(checkoutRepository.findById(checkoutId)).thenReturn(Optional.of(checkout));
        assertEquals(checkout, checkoutServiceImplementation.getCheckoutByCheckoutId(checkoutId));
    }


//	@Test
//	public void addProfileTest() {
//		Profile profile = new Profile("123lj123lj", "FW", "tata", "punch", "123445155512");
//
//		when(profileRepository.save(profile)).thenReturn(profile);
////		assertEquals(profile.getRequestId().getClass(), profileService.addProfile(profile).getClass());
//		assertTrue(new ReflectionEquals(profile.getRequestId(), new String[]{"requestId", "id"}).matches(profileService.addProfile(profile)));
//	}

    @Test
    public void deleteProfileTest() {
        String requestId = "123445155512";
        profileService.deleteProfile(requestId);
        verify(profileRepository, times(1)).deleteByRequestId(requestId);
    }

    @Test
    public void deleteQuotationTest() {
        String requestId = "123445155512";
        quotationServiceImplementation.deleteQuotation(requestId);
        verify(quotationRepository, times(1)).deleteById(requestId);
    }

    @Test
    public void deleteCheckout() {
        String checkoutId = "123445155512";
        checkoutServiceImplementation.deleteCheckout(checkoutId);
        verify(checkoutRepository, times(1)).deleteById(checkoutId);
    }

}
